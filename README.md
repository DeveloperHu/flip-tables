Flip Tables
===========

Because pretty-printing text tables in Java should be easy.

(╯°□°）╯︵ ┻━┻



Usage
-----

A `FlipTable` requires headers and data in string form:
```java
String[] headers = { "Test", "Header" };
String[][] data = {
    { "Foo", "Bar" },
    { "Kit", "Kat" },
};
System.out.println(FlipTable.of(headers, data));
```
```
╔══════╤════════╗
║ Test │ Header ║
╠══════╪════════╣
║ Foo  │ Bar    ║
╟──────┼────────╢
║ Kit  │ Kat    ║
╚══════╧════════╝
```

They can be empty:
```java
String[] headers = { "Test", "Header" };
String[][] data = {};
System.out.println(FlipTable.of(headers, data));
```
```
╔══════╤════════╗
║ Test │ Header ║
╠══════╧════════╣
║ (empty)       ║
╚═══════════════╝
```

Newlines are supported:
```java
String[] headers = { "One Two\nThree", "Four" };
String[][] data = { { "Five", "Six\nSeven Eight" } };
System.out.println(FlipTable.of(headers, data));
```
```
╔═════════╤═════════════╗
║ One Two │ Four        ║
║ Three   │             ║
╠═════════╪═════════════╣
║ Five    │ Six         ║
║         │ Seven Eight ║
╚═════════╧═════════════╝
```

Which means tables can be nested:
```java
String[] innerHeaders = { "One", "Two" };
String[][] innerData = { { "1", "2" } };
String inner = FlipTable.of(innerHeaders, innerData);
String[] headers = { "Left", "Right" };
String[][] data = { { inner, inner } };
System.out.println(FlipTable.of(headers, data));
```
```
╔═══════════════╤═══════════════╗
║ Left          │ Right         ║
╠═══════════════╪═══════════════╣
║ ╔═════╤═════╗ │ ╔═════╤═════╗ ║
║ ║ One │ Two ║ │ ║ One │ Two ║ ║
║ ╠═════╪═════╣ │ ╠═════╪═════╣ ║
║ ║ 1   │ 2   ║ │ ║ 1   │ 2   ║ ║
║ ╚═════╧═════╝ │ ╚═════╧═════╝ ║
╚═══════════════╧═══════════════╝
```

Helper methods convert from types like lists:
```java
List<Person> people = Arrays.asList(new Person("Foo", "Bar"), new Person("Kit", "Kat"));
System.out.println(FlipTableConverters.fromIterable(people, Person.class));
```
```
╔═══════════╤══════════╗
║ FirstName │ LastName ║
╠═══════════╪══════════╣
║ Foo       │ Bar      ║
╟───────────┼──────────╢
║ Kit       │ Kat      ║
╚═══════════╧══════════╝
```

Or a database result:
```java
ResultSet resultSet = statement.executeQuery("SELECT first_name, last_name FROM users");
System.out.println(FlipTableConverters.fromResultSet(resultSet));
```
```
╔════════════╤═══════════╗
║ first_name │ last_name ║
╠════════════╪═══════════╣
║ Jake       │ Wharton   ║
╟────────────┼───────────╢
║ Edward     │ Snowden   ║
╚════════════╧═══════════╝
```

Arbitrary objects are also supported:
```java
String[] headers = { "First Name", "Last Name", "Age", "Type" };
Object[][] data = {
    { "Big", "Bird", 16, PersonType.COSTUME },
    { "Joe", "Smith", 42, PersonType.HUMAN },
    { "Oscar", "Grouchant", 8, PersonType.PUPPET }
};
System.out.println(FlipTableConverters.fromObjects(headers, data));
```
```
╔════════════╤═══════════╤═════╤═════════╗
║ First Name │ Last Name │ Age │ Type    ║
╠════════════╪═══════════╪═════╪═════════╣
║ Big        │ Bird      │ 16  │ COSTUME ║
╟────────────┼───────────┼─────┼─────────╢
║ Joe        │ Smith     │ 42  │ HUMAN   ║
╟────────────┼───────────┼─────┼─────────╢
║ Oscar      │ Grouchant │ 8   │ PUPPET  ║
╚════════════╧═══════════╧═════╧═════════╝
```

TableFormat 
--------

There are two available `TableFormat`s.

**DefaultTableFormat**

`System.out.println(FlipTable.of(headers, data));`

OR

`System.out.println(FlipTable.of(new DefaultTableFormat(), headers, data));`

```
╔══════╤════════╗
║ Test │ Header ║
╠══════╪════════╣
║ Foo  │ Bar    ║
╟──────┼────────╢
║ Kit  │ Kat    ║
╚══════╧════════╝
```

**AsciiTableFormat**

`System.out.println(FlipTable.of(new AsciiTableFormat(), headers, data));`

```
+======+========+
| Test | Header |
|======|========|
| Foo  | Bar    |
|------|--------|
| Kit  | Kat    |
+------+--------+
```


~~Download~~
--------

~~Download the [latest jar][1] or reference on Maven central as
`com.jakewharton.fliptables:fliptables:1.0.2`.~~

~~Snapshots of the development version are available in [Sonatype's `snapshots` repository][snap].~~

Tips
----

This version is not merged into the master branch. so, you can download this source to include your project~

License
-------

    Copyright 2014 Jake Wharton

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.



 [1]: https://search.maven.org/remote_content?g=com.jakewharton.fliptables&a=fliptables&v=LATEST
 [snap]: https://oss.sonatype.org/content/repositories/snapshots/  
 

Other tables
------------
* https://stackoverflow.com/questions/2745206/output-in-a-table-format-in-javas-system-out  
* https://github.com/cwjcsu/joutable
* https://github.com/RuedigerMoeller/j-text-utils
* https://github.com/thedathoudarya/WAGU-data-in-table-view
 
 
中文等宽支持
-------
* 使用AsciiTableFormat
* 解决中文和英文等宽问题不应该从代码入手，应该从字体入手，选择一款中英文等宽的字体，并在控制台输出使用这种等宽字体即可解决
    * 引用解决方案：https://www.v2ex.com/t/26972
    * 如果是想要“一个汉字＝2个英文字母”的宽度, M+ 1M 这个字体就行，https://mplus-fonts.osdn.jp/about-en.html
    * 下载并解压 mplus-TESTFLIGHT-046.tar.xz 字体文件 https://zh.osdn.net/projects/mplus-fonts/downloads/62344/mplus-TESTFLIGHT-063.tar.xz/
    * 解压后文件夹里有很多字体，有些是非等宽的，有些是等宽的。mplus-1m-regular.ttf 这个是“一个汉字＝2个英文字母”的宽度。
