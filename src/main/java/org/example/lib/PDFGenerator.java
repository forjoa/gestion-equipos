package org.example.lib;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import org.example.models.Player;
import org.example.models.Team;
import org.example.models.TeamPlayers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class PDFGenerator {
    public static void generateTeamsReport(List<TeamPlayers> teams) {
        String dest = "teams_report.pdf";

        try {
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            for (TeamPlayers teamPlayers : teams) {
                Team team = teamPlayers.getTeam();
                List<Player> players = teamPlayers.getPlayersList();

                // Agregar título del equipo
                Paragraph teamTitle = new Paragraph(team.getName())
                        .setBold()
                        .setFontSize(18)
                        .setTextAlignment(TextAlignment.CENTER);
                document.add(teamTitle);

                // Agregar información general del equipo
                document.add(new Paragraph("Ciudad: " + team.getCity()));
                document.add(new Paragraph("Estadio: " + team.getStadium()));
                document.add(new Paragraph(" "));

                // Lista numerada de jugadores
                com.itextpdf.layout.element.List list = new com.itextpdf.layout.element.List().setSymbolIndent(12).setListSymbol("•");
                int count = 1;
                for (Player player : players) {
                    list.add(new ListItem(count + ". " + player.getName() + " - " + player.getPosition()));
                    count++;
                }
                document.add(list);

                // Salto de línea entre equipos
                document.add(new Paragraph(" ").setFontSize(12));
                document.add(new Paragraph("---------------------------------------").setTextAlignment(TextAlignment.CENTER));
                document.add(new Paragraph(" "));
            }

            document.close();
            System.out.println("PDF generado en: " + new File(dest).getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}