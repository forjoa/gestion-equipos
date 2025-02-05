package org.example.lib;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.models.Player;
import org.example.models.Team;
import org.example.models.TeamPlayers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Class that works as an excel generator
 * @author Joaquin Trujillo
 */
public class ExcelGenerator {
    /**
     * Method to build an Excel book based in the list of teams
     * @param teams all the teams information
     * @param teamDAO class to ask for each team players
     * @throws IOException exception
     */
    public static void generateTeamsExcel(List<Team> teams, TeamDAO teamDAO) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Equipos y Jugadores");

        int rowNum = 0;

        // team table header
        Row headerRow = sheet.createRow(rowNum++);
        String[] headers = {"ID", "Nombre", "Ciudad", "Estadio"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // writes team in principal table
        for (Team team : teams) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(team.getId());
            row.createCell(1).setCellValue(team.getName());
            row.createCell(2).setCellValue(team.getCity());
            row.createCell(3).setCellValue(team.getStadium());
        }

        // leave blank row before each team table
        rowNum++;

        // write players ordered by team on separated tables
        for (Team team : teams) {
            TeamPlayers teamPlayers = teamDAO.getAllTeamPlayersById(team.getId());
            List<Player> players = teamPlayers.getPlayersList();

            // team title (name)
            Row titleRow = sheet.createRow(rowNum++);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("⚽ Jugadores de " + team.getName() + " ⚽");

            // players header
            Row playerHeaderRow = sheet.createRow(rowNum++);
            String[] playerHeaders = {"ID", "Nombre", "Posición"};
            for (int i = 0; i < playerHeaders.length; i++) {
                Cell cell = playerHeaderRow.createCell(i);
                cell.setCellValue(playerHeaders[i]);
            }

            // write all players
            for (Player player : players) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(player.getId());
                row.createCell(1).setCellValue(player.getName());
                row.createCell(2).setCellValue(player.getPosition());
            }

            // leave blank row before each team table
            rowNum++;
        }

        // save file
        try (FileOutputStream fileOut = new FileOutputStream("teams_report.xlsx")) {
            workbook.write(fileOut);
        }

        workbook.close();
        System.out.println("Excel generado exitosamente: teams_report.xlsx");
    }
}

