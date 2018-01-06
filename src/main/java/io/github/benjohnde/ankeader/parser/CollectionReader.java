package io.github.benjohnde.ankeader.parser;

import io.github.benjohnde.ankeader.parser.collection.Card;
import org.sormula.Database;
import org.sormula.Table;
import org.sqlite.SQLiteConfig;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collections;
import java.util.List;

public class CollectionReader {
    private Connection connection;
    private Database database;

    public CollectionReader(String tmp) throws Exception {
        File databaseFile = new File(tmp, "collection.anki2");
        initSqliteConnection(databaseFile);

        Table<Card> tableCards = database.getTable(Card.class);
        List<Card> cards = Collections.unmodifiableList(tableCards.selectAll());

        for (Card card : cards) {
            // System.out.println(card);
        }

        System.out.println(cards.size() + " cards were detected and parsed.");

        // finally
        connection.close();
    }

    private void initSqliteConnection(File databaseFile) throws Exception {
        Class.forName("org.sqlite.JDBC");

        SQLiteConfig config = new SQLiteConfig();
        config.setReadOnly(true);

        String url = "jdbc:sqlite:" + databaseFile.getAbsolutePath();

        connection = DriverManager.getConnection(url, config.toProperties());
        database = new Database(connection);
    }
}
