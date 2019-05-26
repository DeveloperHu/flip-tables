/*
 * Copyright 2014 Jake Wharton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jakewharton.fliptables;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class FlipTableTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void empty() {
        String[] headers = {"Test", "Header"};
        String[][] data = {};
        String expected = ""
                + "╔══════╤════════╗\n"
                + "║ Test │ Header ║\n"
                + "╠══════╧════════╣\n"
                + "║ (empty)       ║\n"
                + "╚═══════════════╝\n";
        String of = FlipTable.of(headers, data);
        System.out.println(of);
        assertThat(of).isEqualTo(expected);
    }

    @Test
    public void emptyWide() {
        String[] headers = {"Test", "Headers", "Are", "The", "Best"};
        String[][] data = {};
        String expected = ""
                + "╔══════╤═════════╤═════╤═════╤══════╗\n"
                + "║ Test │ Headers │ Are │ The │ Best ║\n"
                + "╠══════╧═════════╧═════╧═════╧══════╣\n"
                + "║ (empty)                           ║\n"
                + "╚═══════════════════════════════════╝\n";
        String of = FlipTable.of(headers, data);
        System.out.println(of);
        assertThat(of).isEqualTo(expected);
    }

    @Test
    public void emptyThinOneColumn() {
        String[] headers = {"A"};
        String[][] data = {};
        String expected = ""
                + "╔═════════╗\n"
                + "║ A       ║\n"
                + "╠═════════╣\n"
                + "║ (empty) ║\n"
                + "╚═════════╝\n";
        String of = FlipTable.of(headers, data);
        System.out.println(of);
        assertThat(of).isEqualTo(expected);
    }

    @Test
    public void emptyThinTwoColumns() {
        String[] headers = {"A", "B"};
        String[][] data = {};
        String expected = ""
                + "╔═══╤═════╗\n"
                + "║ A │ B   ║\n"
                + "╠═══╧═════╣\n"
                + "║ (empty) ║\n"
                + "╚═════════╝\n";
        String of = FlipTable.of(headers, data);
        System.out.println(of);
        assertThat(of).isEqualTo(expected);
    }

    @Test
    public void simple() {
        String[] headers = {"Hi", "Header"};
        String[][] data = { //
                {"Foo", "Bar"}, //
                {"Kit", "Kat"}, //
                {"Ping", "Pong"}, //
        };
        String expected = ""
                + "╔══════╤════════╗\n"
                + "║ Hi   │ Header ║\n"
                + "╠══════╪════════╣\n"
                + "║ Foo  │ Bar    ║\n"
                + "╟──────┼────────╢\n"
                + "║ Kit  │ Kat    ║\n"
                + "╟──────┼────────╢\n"
                + "║ Ping │ Pong   ║\n"
                + "╚══════╧════════╝\n";
        String of = FlipTable.of(headers, data);
        System.out.println(of);
        assertThat(of).isEqualTo(expected);
    }


    @Test
    public void simpleAscii() {
        String[] headers = {"Hi", "Header"};
        String[][] data = { //
                {"Foo", "Bar"}, //
                {"Kit", "Kat"}, //
                {"Ping", "Pong"}, //
        };
        String expected = ""
                + "+======+========+\n"
                + "| Hi   | Header |\n"
                + "|======|========|\n"
                + "| Foo  | Bar    |\n"
                + "|------|--------|\n"
                + "| Kit  | Kat    |\n"
                + "|------|--------|\n"
                + "| Ping | Pong   |\n"
                + "+------+--------+\n";
        assertThat(FlipTable.of(new AsciiTableFormat(), headers, data)).isEqualTo(expected);
    }

    @Test
    public void dataNewlineFirstLineLong() {
        String[] headers = {"One", "Two"};
        String[][] data = {{"Foo Bar\nBaz", "Two"}};
        String expected = ""
                + "╔═════════╤═════╗\n"
                + "║ One     │ Two ║\n"
                + "╠═════════╪═════╣\n"
                + "║ Foo Bar │ Two ║\n"
                + "║ Baz     │     ║\n"
                + "╚═════════╧═════╝\n";
        String of = FlipTable.of(headers, data);
        System.out.println(of);
        assertThat(of).isEqualTo(expected);
    }

    @Test
    public void dataNewlineFirstLineShort() {
        String[] headers = {"One", "Two"};
        String[][] data = {{"Foo\nBar Baz", "Two"}};
        String expected = ""
                + "╔═════════╤═════╗\n"
                + "║ One     │ Two ║\n"
                + "╠═════════╪═════╣\n"
                + "║ Foo     │ Two ║\n"
                + "║ Bar Baz │     ║\n"
                + "╚═════════╧═════╝\n";
        String of = FlipTable.of(headers, data);
        System.out.println(of);
        assertThat(of).isEqualTo(expected);
    }

    @Test
    public void headerNewlineFirstLineLong() {
        String[] headers = {"One Two\nThree", "Four"};
        String[][] data = {{"One", "Four"}};
        String expected = ""
                + "╔═════════╤══════╗\n"
                + "║ One Two │ Four ║\n" // TODO bottom align this!
                + "║ Three   │      ║\n"
                + "╠═════════╪══════╣\n"
                + "║ One     │ Four ║\n"
                + "╚═════════╧══════╝\n";
        String of = FlipTable.of(headers, data);
        System.out.println(of);
        assertThat(of).isEqualTo(expected);
    }

    @Test
    public void headerNewlineFirstLineShort() {
        String[] headers = {"One\nTwo Three", "Four"};
        String[][] data = {{"One", "Four"}};
        String expected = ""
                + "╔═══════════╤══════╗\n"
                + "║ One       │ Four ║\n" // TODO bottom align this!
                + "║ Two Three │      ║\n"
                + "╠═══════════╪══════╣\n"
                + "║ One       │ Four ║\n"
                + "╚═══════════╧══════╝\n";
        String of = FlipTable.of(headers, data);
        System.out.println(of);
        assertThat(of).isEqualTo(expected);
    }

    @Test
    public void nested() {
        String[] innerHeaders = {"One", "Two"};
        String[][] innerData = {{"1", "2"}};
        String nested = FlipTable.of(innerHeaders, innerData);
        String[] outerHeaders = {"Left", "Right"};
        String[][] outerData = {{nested, nested}};
        String expected = ""
                + "╔═══════════════╤═══════════════╗\n"
                + "║ Left          │ Right         ║\n"
                + "╠═══════════════╪═══════════════╣\n"
                + "║ ╔═════╤═════╗ │ ╔═════╤═════╗ ║\n"
                + "║ ║ One │ Two ║ │ ║ One │ Two ║ ║\n"
                + "║ ╠═════╪═════╣ │ ╠═════╪═════╣ ║\n"
                + "║ ║ 1   │ 2   ║ │ ║ 1   │ 2   ║ ║\n"
                + "║ ╚═════╧═════╝ │ ╚═════╧═════╝ ║\n"
                + "╚═══════════════╧═══════════════╝\n";
        String of = FlipTable.of(outerHeaders, outerData);
        System.out.println(of);
        assertThat(of).isEqualTo(expected);
    }

    @Test
    public void rowColumnMismatchThrowsWhenLess() {
        String[] headers = {"The", "Headers"};

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Row 1's 1 columns != 2 columns");

        String[][] less = {{"Less"}};
        FlipTable.of(headers, less);
    }

    @Test
    public void rowColumnMismatchThrowsWhenMore() {
        String[] headers = {"The", "Headers"};

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Row 1's 4 columns != 2 columns");

        String[][] more = {{"More", "Is", "Not", "Less"}};
        FlipTable.of(headers, more);
    }

    @Test
    public void nullHeadersThrows() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("headers == null");

        FlipTable.of(null, new String[0][0]);
    }

    @Test
    public void emptyHeadersThrows() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Headers must not be empty.");

        FlipTable.of(new String[0], new String[0][0]);
    }

    @Test
    public void nullDataThrows() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("data == null");

        FlipTable.of(new String[1], null);
    }
}
