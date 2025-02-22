package br.com.dio.persistence.migration;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;

import lombok.AllArgsConstructor;
import lombok.var;

@AllArgsConstructor
public class MigrationStrategy {

    private final Connection connection;

    private void executeMigration() {

        var originalOut = System.out;
        var originalErr = System.err;

        try {
            try (var fos = new FileOutputStream("liquibase.log")) {

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            System.setOut(originalOut);
            System.setErr(originalErr);
        }
    }
}

//15:34
