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

package domain.main;

import domain.database.MyDataSource;
import domain.database.PgPerson;
import domain.entite.Husband;
import domain.entite.Person;
import domain.entite.Teacher;
import java.util.logging.Logger;

/**
 * Entry point.
 *
 * @since 0.1
 */
public final class Main {

    /**
     * Logger.
     */
    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    /**
     * Ctor.
     */
    private Main() { }

    /**
     * Main.
     * @param args Arguments
     */
    @SuppressWarnings("PMD.ProhibitPublicStaticMethods")
    public static void main(final String... args) {
        LOG.info("I take from PostgreSQL database a teacher that is a husband");
        final Person person =
            new Husband(
                new Teacher(
                    new PgPerson(new MyDataSource(), 1)
                )
            );
        LOG.info("I rename this person");
        LOG.info("All rules relative to teacher and husband are applied");
        LOG.info("before saving name into database");
        person.rename("Olivier Baudouin OURA");
    }

}
