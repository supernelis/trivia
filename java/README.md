# Instructions

## Run Test

```bash
$ mvn clean test
```
## Run Mutation Testing

```bash
$ mvn -DwithHistory org.pitest:pitest-maven:mutationCoverage
```

open in a browser the html report `target/pit-reports/<a date here>/index.html`