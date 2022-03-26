package petut.webDysgraphie.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "points",
    "vitesses",
    "accelerations",
    "jerks",
    "tempsDebut",
    "version",
    "pixelToCm"
})
public class Tableau {

    //Paramètre
    private String version = "v.1.14";
    private double pixelToCm = 0.02646;

    //Attribut
    private long tempsDebut;
    private Points points;
    private Vitesses vitesses;
    private Accelerations accelerations;
    private Jerks jerks;

    public Tableau(Points points) {
        this.points = points;
        calculerVitesseJerkAcceleration();
    }

    public Tableau() {
    }

    public Points getPoints() {
        return points;
    }

    public Vitesses getVitesses() {
        return vitesses;
    }

    public Accelerations getAccelerations() {
        return accelerations;
    }

    public Jerks getJerks() {
        return jerks;
    }

    public void setPoints(Points points) {
        this.points = points;
    }

    public void setVitesses(Vitesses vitesses) {
        this.vitesses = vitesses;
    }

    public void setAccelerations(Accelerations accelerations) {
        this.accelerations = accelerations;
    }

    public void setJerks(Jerks jerks) {
        this.jerks = jerks;
    }

    @Override
    public String toString() {
        return "Tableau{" + "points=" + points + ", vitesses=" + vitesses + ", acceleration=" + accelerations + ", jerks=" + jerks + '}';
    }

    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    private void calculerVitesseJerkAcceleration() {
        ArrayList<Point> listPoint = points.getPoints();
        //Liste des coordonnées y dans le bon plan 
        for (Point p : listPoint) {
            p.setY((int) (p.getY() * -1));
        }
        tempsDebut = listPoint.get(0).getTime();
        for (int i = 0; i < listPoint.size(); i++) {
            if (i > 1) {
                double dist = listPoint.get(i).distanceAvec(listPoint.get(i - 1));
                dist = dist * pixelToCm;//Convertion en cm
                // Vitesse (J)
                int deriveTemps = listPoint.get(i).getInterval();
                double vit = dist / deriveTemps;

                vitesses.getVitesses().add(new Vitesse(listPoint.get(i).getTime(), vit));

            }

            if (i > 2) {
                int deriveTemps1 = listPoint.get(i).getTime() - listPoint.get(i - 1).getTime();
                double dist1 = listPoint.get(i).distanceAvec(listPoint.get(i - 1));
                double vit1 = dist1 / deriveTemps1;

                int deriveTemps2 = listPoint.get(i - 1).getTime() - listPoint.get(i - 2).getTime();
                double dist2 = listPoint.get(i - 1).distanceAvec(listPoint.get(i - 2));

                double vit2 = dist2 / deriveTemps2;

                double acc = (vit1 - vit2) / (deriveTemps1 - deriveTemps2);
                if ((acc > 0 || acc < 0) && acc != Double.POSITIVE_INFINITY && acc != Double.NEGATIVE_INFINITY) {
                    accelerations.getAccelerations().add(new Acceleration(listPoint.get(i).getTime(), acc));
                }
            }
            if (i > 3) {
                double d1 = listPoint.get(i - 2).distanceAvec(listPoint.get(i - 3));
                double d2 = listPoint.get(i - 1).distanceAvec(listPoint.get(i - 2));
                double d3 = listPoint.get(i).distanceAvec(listPoint.get(i - 1));

                double v1 = d1 / (listPoint.get(i - 2).getInterval());
                double v2 = d2 / (listPoint.get(i - 1).getInterval());
                double v3 = d3 / (listPoint.get(i).getInterval());

                int deriveTemps1 = listPoint.get(i - 2).getTime() - listPoint.get(i - 3).getTime();
                int deriveTemps2 = listPoint.get(i - 1).getTime() - listPoint.get(i - 2).getTime();
                int deriveTemps3 = listPoint.get(i).getTime() - listPoint.get(i - 1).getTime();

                double a1 = (v2 - v1) / (deriveTemps2 - deriveTemps1);
                double a2 = (v3 - v2) / (deriveTemps3 - deriveTemps2);

                double jerk = (a2 - a1) / ((deriveTemps3 - deriveTemps2) - (deriveTemps2 - deriveTemps1));
                if ((jerk > 0 || jerk < 0) && jerk != Double.POSITIVE_INFINITY && jerk != Double.NEGATIVE_INFINITY) {
                    jerks.getJerks().add(new Jerk(listPoint.get(i).getTime(), jerk));
                }
            }
        }
    }

    public void DownloadExcel(String fileName, String sheetName) {
        ArrayList<Point> listPoint = points.getPoints();
        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet(sheetName);
            int rownum = 0;
            Cell cell;
            Row row;
            //
            HSSFCellStyle style = createStyleForTitle(workbook);
            row = sheet.createRow(rownum);
            // Seg
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Seg");
            cell.setCellStyle(style);
            // Num
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Num");
            cell.setCellStyle(style);
            // X/100mm
            cell = row.createCell(2, CellType.STRING);
            //cell.setCellValue("X/100mm");
            cell.setCellValue("X (cm)");
            cell.setCellStyle(style);
            // Y/100mm
            cell = row.createCell(3, CellType.STRING);
            //cell.setCellValue("Y/100mm");
            cell.setCellValue("Y (cm)");
            cell.setCellStyle(style);
            // Interv ms
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Interv (ms)");
            cell.setCellStyle(style);
            // Time ms
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Time ms");
            cell.setCellStyle(style);
            // Tip
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Tip");
            cell.setCellStyle(style);
            // P
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("P");
            cell.setCellStyle(style);
            // Az
            cell = row.createCell(8, CellType.STRING);
            //cell.setCellValue("Az");
            cell.setCellValue("Distance (cm)");
            cell.setCellStyle(style);
            // Al
            cell = row.createCell(9, CellType.STRING);
            //cell.setCellValue("Al");
            cell.setCellValue("Vitesse (cm/ms)");
            cell.setCellStyle(style);
            cell = row.createCell(10, CellType.STRING);
            cell.setCellValue("Accélération (cm/ms-2)");
            cell.setCellStyle(style);
            cell = row.createCell(11, CellType.STRING);
            //cell.setCellValue("Al");
            cell.setCellValue("Pics d'accélération");
            cell.setCellStyle(style);
            cell = row.createCell(12, CellType.STRING);
            cell.setCellValue("Jerk");
            cell.setCellStyle(style);

            cell = row.createCell(13, CellType.STRING);
            cell.setCellValue("Moyenne vitesse");
            cell.setCellStyle(style);

            cell = row.createCell(14, CellType.STRING);
            cell.setCellValue("Ecart-Type vitesse");
            cell.setCellStyle(style);

            cell = row.createCell(15, CellType.STRING);
            cell.setCellValue("Moyenne accélération");
            cell.setCellStyle(style);

            cell = row.createCell(16, CellType.STRING);
            cell.setCellValue("Ecart-Type accélération");
            cell.setCellStyle(style);

            cell = row.createCell(17, CellType.STRING);
            cell.setCellValue("Moyenne Jerk");
            cell.setCellStyle(style);

            cell = row.createCell(18, CellType.STRING);
            cell.setCellValue("Ecart-Type Jerk");
            cell.setCellStyle(style);

            // Data
            for (int i = 0; i < listPoint.size(); i++) {
                rownum++;
                row = sheet.createRow(rownum);

                // Seg (A)
                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(0);
                // Num (B)
                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(listPoint.get(i).getNum());
                // X (C)
                cell = row.createCell(2, CellType.NUMERIC);
                cell.setCellValue(listPoint.get(i).getX() * pixelToCm);
                // Y (D)
                cell = row.createCell(3, CellType.NUMERIC);
                cell.setCellValue(listPoint.get(i).getY() * pixelToCm);
                // Interv ms (E)
                cell = row.createCell(4, CellType.NUMERIC);
                cell.setCellValue(listPoint.get(i).getInterval());
                // Time ms (F)
                cell = row.createCell(5, CellType.NUMERIC);
                cell.setCellValue(listPoint.get(i).getTime() - tempsDebut);
                // P (H)
                cell = row.createCell(7, CellType.NUMERIC);
                //METTRE LA VALEUR DE LA PRESSION
                cell.setCellValue(2);
                // Tip (G)
                int num;
                if (cell.getNumericCellValue() > 0) {
                    num = 1;
                } else {
                    num = 0;
                }
                cell = row.createCell(6, CellType.NUMERIC);
                cell.setCellValue(num);
                // Distance (I)
                if (rownum > 1) {
                    double dist = listPoint.get(i).distanceAvec(listPoint.get(i - 1));
                    dist = dist * pixelToCm;//Convertion en cm
                    cell = row.createCell(8, CellType.NUMERIC);
                    cell.setCellValue(dist);
                    cell = row.createCell(9, CellType.NUMERIC);
                    cell.setCellValue(vitesses.getVitesses().get(i).getY());

                }
                // Accélération (K)
                if (rownum > 2) {
                    double nbP = 0;
                    double acc = accelerations.getAccelerations().get(i).getY();
                    cell = row.createCell(10, CellType.NUMERIC);
                    cell.setCellValue(acc);

                    // Pics
                    if (acc > 15.0) {
                        nbP = 1;
                    } else {
                        if (acc < -15.0) {
                            nbP = -1;
                        }
                    }
                    cell = row.createCell(11, CellType.NUMERIC);
                    cell.setCellValue(nbP);

                }

                //Jerk (M)
                if (rownum > 3) {
                    double jerk = jerks.getJerks().get(i).getY();
                    cell = row.createCell(12, CellType.NUMERIC);
                    cell.setCellValue(jerk);

                }
            }
            /*
            //Calcul des moyennes et écart-type 
            row = sheet.createRow(1);
            //Vitesse
            cell = row.createCell(13, CellType.NUMERIC);
            cell.setCellValue(moyenne(vitesses));
            cell = row.createCell(14, CellType.NUMERIC);
            cell.setCellValue(ecartType(vitesses));
            //Accélération
            cell = row.createCell(15, CellType.NUMERIC);
            cell.setCellValue(moyenne(accelerations));

            cell = row.createCell(16, CellType.NUMERIC);
            cell.setCellValue(ecartType(accelerations));
            System.out.println(accelerations);
            //Jerk
            cell = row.createCell(17, CellType.NUMERIC);
            cell.setCellValue(moyenne(jerks));

            cell = row.createCell(18, CellType.NUMERIC);
            cell.setCellValue(ecartType(jerks));*/

            File file = new File("./Dataset/" + fileName);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);
            System.out.println("Created file: " + file.getAbsolutePath());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tableau.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Tableau.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                outFile.close();
            } catch (IOException ex) {
                Logger.getLogger(Tableau.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private double moyenne(ArrayList<Double> list) {
        double S = 0;
        for (double l : list) {
            S = S + l;
        }
        return S / list.size();
    }

    private double ecartType(ArrayList<Double> list) {
        double S = 0;
        double m = moyenne(list);
        for (double l : list) {
            S = S + Math.pow(l - m, 2);
        }
        return Math.sqrt(S / list.size());
    }
}
