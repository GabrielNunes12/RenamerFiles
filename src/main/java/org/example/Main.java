package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Boolean isFinishedAppendingNames = true;
    String fileDir, fileName, choice;
    List<String> fileNames = new ArrayList<>();
    System.out.println("Digite o caminho até o arquivo: ");
    fileDir = scanner.nextLine();
    File readFile = new File(fileDir);
    while (isFinishedAppendingNames) {
      System.out.println("Digite o(s) nome(s) dos arquivos que você quer trocar o nome: ");
      fileName = scanner.nextLine();
      fileNames.add(fileName);
      System.out.println("Deseja adicionar mais? [S/N]");
      choice = scanner.nextLine();
      isFinishedAppendingNames = choice.equalsIgnoreCase("s");
    }
    System.out.println("-------Processando arquivo-------");
    if(readFile.exists() && readFile.isDirectory()) {
      //Not optimal
      Arrays.asList(readFile.listFiles()).forEach(file -> {
        fileNames.forEach(fileNameToBeRenamed -> {
          if(file.isFile() && file.getName().contains(fileNameToBeRenamed)) {
            String newFileName = file.getName().replace(fileNameToBeRenamed, fileNameToBeRenamed + " - cancelado");
            File newFile = new File(readFile, newFileName);
            if (file.renameTo(newFile)) {
              System.out.println("Renomeado de: " + file.getName() + " para " + newFileName);
            } else {
              System.out.println("Falha ao tentar renomear o arquivo: " + file.getName());
            }
          }
        });
      });
    }
    scanner.close();
  }
}