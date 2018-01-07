package io.github.benjohnde.ankeader.parser.orm;

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
    private List<CardEntity> cards;

    public CollectionReader(String tmp) throws Exception {
        File databaseFile = new File(tmp, "collection.anki2");
        initSqliteConnection(databaseFile);
        this.cards = readCards();

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

    private List<CardEntity> readCards() throws Exception {
        Table<CardEntity> tableCards = database.getTable(CardEntity.class);
        List<CardEntity> cards = Collections.unmodifiableList(tableCards.selectAll());
        return cards;
    }

    public List<CardEntity> getCards() {
        return cards;
    }
}
