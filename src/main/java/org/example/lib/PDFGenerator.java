package org.example.lib;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import org.example.models.Player;
import org.example.models.Team;
import org.example.models.TeamPlayers;

import java.io.FileNotFoundException;
import java.util.List;
/**
 * Class that works as a pdf generator
 * @author Joaquin Trujillo
 */
public class PDFGenerator {
    /**
     * Method to build a PDF based on all the teams detailed information
     * @param teams list of all the team information and each player list
     */
    public static void generateTeamsReport(List<TeamPlayers> teams) {
        String destination = "teams_report.pdf";

        try {
            PdfWriter writer = new PdfWriter(destination);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            for (TeamPlayers teamPlayers : teams) {
                Team team = teamPlayers.getTeam();
                List<Player> players = teamPlayers.getPlayersList();

                // add team's title
                Paragraph teamTitle = new Paragraph(team.getName())
                        .setBold()
                        .setFontSize(18)
                        .setTextAlignment(TextAlignment.CENTER);
                document.add(teamTitle);

                // add team information
                document.add(new Paragraph("Ciudad: " + team.getCity()));
                document.add(new Paragraph("Estadio: " + team.getStadium()));
                document.add(new Paragraph(" "));

                // ordered team members
                com.itextpdf.layout.element.List list = new com.itextpdf.layout.element.List().setSymbolIndent(12).setListSymbol("•");
                int count = 1;
                for (Player player : players) {
                    list.add(new ListItem(count + ". " + player.getName() + " - " + player.getPosition()));
                    count++;
                }
                document.add(list);

                // line between teams
                document.add(new Paragraph(" ").setFontSize(12));
                document.add(new Paragraph("----------------------------------------------").setTextAlignment(TextAlignment.CENTER));
                document.add(new Paragraph(" "));
            }

            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}