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

package domain.entite;

import java.util.logging.Logger;

/**
 * {@link Person} decorator that defines a student.
 *
 * <p>The class is immutable and thread-safe.
 *
 * @since 0.0.1
 */
public final class Student implements Person {

    /**
     * Logger.
     */
    private static final Logger LOG = Logger.getLogger(Student.class.getName());

    /**
     * Person to decorate.
     */
    private final Person origin;

    /**
     * Ctor.
     * @param origin Origin
     */
    public Student(final Person origin) {
        this.origin = origin;
    }

    @Override
    public String name() {
        return this.origin.name();
    }

    @Override
    public void rename(final String name) {
        LOG.info("Apply here rules about student renaming");
        LOG.info("Especially about terms relative to his school");
        LOG.info("or national education ministry");
        this.origin.rename(name);
    }

    @Override
    public String toString() {
        return String.format("Student %s", this.name());
    }

    @Override
    public boolean equals(final Object obj) {
        return this.origin.equals(obj);
    }

    @Override
    public int hashCode() {
        return this.origin.hashCode();
    }
}
