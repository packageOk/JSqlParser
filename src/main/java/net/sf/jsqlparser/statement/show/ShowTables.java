/*
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2017 JSQLParser
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package net.sf.jsqlparser.statement.show;

import net.sf.jsqlparser.schema.Database;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.StatementVisitor;

public class ShowTables implements Statement {
    private String databaseModifier;
    private Database database;
    private String pattern;

    @Override
    public void accept(StatementVisitor statementVisitor) {
        statementVisitor.visit(this);
    }

    @Override
    public String toString() {
        StringBuilder sql = new StringBuilder();
        sql.append("SHOW TABLES");
        if (database != null) {
            sql.append(" ").append(databaseModifier).append(" ").append(database.getDatabaseName());
        }
        if (pattern != null) {
            sql.append(" ").append("LIKE ").append(pattern);
        }
        return sql.toString();
    }

    public String getDatabaseModifier() {
        return databaseModifier;
    }

    public void setDatabaseModifier(String databaseModifier) {
        this.databaseModifier = databaseModifier;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }



    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
