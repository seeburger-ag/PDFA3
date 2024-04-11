
Mustangproject
=====

Source code repository for the [Mustang project](http://www.mustangproject.org/) open source java PDF invoice metadata library in ZUGFeRD format.

Build
-----

These are the recommended dependencies for the project:

 - OpenJDK 21.0.2 2024-01-16
 - Apache Maven 3.9.6

You can build the project with:

```shell
mvn clean install
```

This will run the tests, build the project artefacts, and install the artifacts to the local
cache. After that the mustang JAR can be used.

More information on how to develop **on** mustang:

 - [Developer documentation](https://github.com/ZUGFeRD/mustangproject/blob/master/doc/development_documentation.md)

Usage
-----

If you set up a Maven project, you can reference the mustang artifact like this:

```xml
<dependency>
  <groupId>org.mustangproject</groupId>
  <artifactId>library</artifactId>
 <version>2.11.0</version>
</dependency>
```

Further docs on how to develop **with** mustang:

 - [ZugferdDev.en.pdf](https://github.com/ZUGFeRD/mustangproject/blob/master/doc/ZugferdDev.en.pdf?raw=true): The English mustang user documentation.
 - [ZugferdDev.de.pdf](https://github.com/ZUGFeRD/mustangproject/blob/master/doc/ZugferdDev.de.pdf?raw=true): The German mustang user documentation.
 - [Usage examples](https://www.mustangproject.org/use/): Read and write electronic invoices.
 - [Mustang classes](https://www.mustangproject.org/invoice-class/): Using the mustang classes.

Contact
-----

Developer: Jochen Stärk. For questions please contact Jochen at jstaerk [at] usegroup.de

License
-----

Subject to the [Apache-2.0 license](http://www.apache.org/licenses/LICENSE-2.0.html).

SEEBURGER specific version(s)
-----
The [master](https://github.com/seeburger-ag/PDFA3/tree/master) branch of this GitHub repository is always periodically
synchronized with the upstream Mustang project and contains no SEEBURGER specific modifications.

The [seeburger](https://github.com/seeburger-ag/PDFA3/tree/seeburger) branch contains a version of Mustang 1.x as used in
some old versions of SEEBURGER products and may receive additional security fixes if necessary.

The branch [seeburgerMustang2](https://github.com/seeburger-ag/PDFA3/tree/seeburgerMustang2) contains the up-to-date version
of Mustang 2.x that is used in SEEBURGER products. Slight differences exist in the ZUGFeRD exporter interface (as compared
to the [master](https://github.com/seeburger-ag/PDFA3/tree/master) branch). These differences provide some level of compatibility
with the respective Mustang 1.x API, with respect to the generated ZUGFeRD meta-data.
