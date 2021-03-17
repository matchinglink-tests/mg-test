#Matching link mortgage test
This Spring Boot application contains three REST endpoints providing mortgage cash flow information based on the mortgage portfolio in the provided excel file.
The endpoints implemented are listed below.

##GET - /mortgage
Returns a list of mortgage summary's. For example:
```
[
{"clientCode":"Client1","portfolioCode":"mortgage_port1","identifier":"HYP001","type":"bullet"},
{"clientCode":"Client1","portfolioCode":"mortgage_port1","identifier":"HYP002","type":"bullet"},
{"clientCode":"Client1","portfolioCode":"mortgage_port1","identifier":"HYP003","type":"bullet"},
{"clientCode":"Client1","portfolioCode":"mortgage_port1","identifier":"HYP004","type":"bullet"}, 
...
]
```

##GET - /mortgage/{identifier}
Returns the full details of a mortgage associated with the provided identifier.
```
{
"clientCode":"Client1",
"portfolioCode":"mortgage_port1",
"identifier":"HYP001",
"originalNotional":200000.0,
"type":"bullet",
"currentNotional":150000.0,
"startDate":"2015-03-25",
"endDate":"2045-03-25",
"interestRate":0.03,
"fixedUntil":"2045-03-25",
"currentMonthlyInterest":375.0,
...
}
```

##GET - /mortgage/{identifier}/cashflow
Returns the cash flow for the mortgage associated with the provided identifier. This cash flow starts on 2020-07-01, and runs until the end date of the mortgage.
```
[
{"paymentDate":"2020-07-01","amount":375.0},
{"paymentDate":"2020-08-01","amount":375.0},
{"paymentDate":"2020-09-01","amount":375.0},
{"paymentDate":"2020-10-01","amount":375.0},
{"paymentDate":"2020-11-01","amount":375.0},
{"paymentDate":"2020-12-01","amount":375.0},
{"paymentDate":"2021-01-01","amount":375.0},
...
]
```
