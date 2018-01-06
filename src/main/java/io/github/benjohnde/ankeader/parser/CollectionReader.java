package io.github.benjohnde.ankeader.parser;

import io.github.benjohnde.ankeader.parser.collection.Note;
import org.sormula.Database;
import org.sormula.Table;
import org.sqlite.SQLiteConfig;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CollectionReader {
    Connection connection;

    public CollectionReader(String tmp) throws Exception {
        Class.forName("org.sqlite.JDBC");

        File sqlite3file = new File(tmp, "collection.anki2");

        SQLiteConfig config = new SQLiteConfig();
        config.setReadOnly(true);

        connection = DriverManager.getConnection("jdbc:sqlite:" + sqlite3file.getAbsolutePath(), config.toProperties());

        Database database = new Database(connection);
        Table<Note> tableNotes = database.getTable(Note.class);
        List<Note> notes = Collections.unmodifiableList(tableNotes.selectAll());


        for (Note note : notes) {
            if (note.getAnswers().size() > 1) {
                System.out.println("yeah");
            }
        }

        // finally
        connection.close();
    }
}
