/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020 Olivier B. OURA
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package domain.database;

import domain.entite.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import javax.sql.DataSource;

/**
 * {@link Person} that comes from PostgreSQL database.
 *
 * <p>The class is immutable and thread-safe.
 *
 * @since 0.0.1
 */
public final class PgPerson implements Person {

    /**
    * DataSource.
    */
    private final DataSource source;

    /**
     * Identifier.
     */
    private final int id;

    /**
     * Ctor.
     * @param source DataSource
     * @param id Identifier
     */
    public PgPerson(final DataSource source, final int id) {
        this.source = source;
        this.id = id;
    }

    @Override
    public String name() {
        try (
            Connection connection = this.source.getConnection();
            PreparedStatement stmt =
                connection.prepareStatement(
                    "SELECT name FROM person WHERE id=?"
                )
        ) {
            stmt.setInt(1, this.id);
            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                return rs.getString(1);
            }
        }   catch (final SQLException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    @Override
    public void rename(final String name) {
        try (
            Connection connection = this.source.getConnection();
            PreparedStatement stmt =
                connection.prepareStatement(
                    "UPDATE person SET name=? WHERE id=?"
                )
        ) {
            stmt.setString(1, name);
            stmt.setInt(2, this.id);
            stmt.execute();
        }   catch (final SQLException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    @Override
    public boolean equals(final Object obj) {
        final boolean result;
        if (obj == null) {
            result = false;
        } else if (this == obj) {
            result = true;
        } else if (obj instanceof PgPerson) {
            final PgPerson that = (PgPerson) obj;
            result = Objects.equals(this.id, that.id);
        } else if (obj instanceof Person) {
            final Person that = (Person) obj;
            result = that.equals(this);
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
