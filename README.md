# mortgages

## introduction
@MatchingLink we model all kinds of financial products to be able to analyze it's risk and return over time.
In this assignment we ask you to model the cash flows from one of those products.

In this repo you will find an excel file named 'mortgage_data.xlsx'. This file contains a list of house mortgage loans. It is a small example portfolio that a bank or investor might own.

In the file are 3 types of mortgage loans:

1. type bullet: In this case the lender lends the notional (currentNotional), pays interest over the currentNotional every month (interestRate) and repays the complete notional at the endDate.
2. type linear: In this case the lender lends the notional (currentNotional), pays interest over the currentNotional every month (interestRate) as well as a fixed amount of monthly repayment (currentLinearRepayment) and repays the remaining at the endDate. Please note that the montly repayment reduces the currentNotional.
3. type annuity: In this case the lender lends the notional (currentNotional), pays interest over the currentNotional every month (interestRate) as well as a monthly repayment and repays the remaining at the endDate. Please note that the montly repayment reduces the currentNotional. In case of the annuity the amount payed each month is fixed (currentMonthlyAnnuity) and is the sum of interest and repayment. At the start this is mainly interest and little repayment. At the end it will be mainly repayment (because the currentNotional decreases).

## assignment
We want you to model the payments (cash flows) the lender (bank or invester) receives for each of these loans. Starting at 2020-07-01 until the endDate of each mortgage loan. You may assume that the mortgage rate stays fixed until the endDate. Each cash flow should have a date and an amount. Please note that interestRate is the yearly interest where 0.03 = 3%. In this case the yearly interest of an hypothatical loan with a currentNNotional of EUR 200000 is EUR 6000. Interest is payed per month so that would result in 12 interest cash flows of EUR 500 each.

To so this, please create a kotlin web application that reads the excel file at startup. Please provide a REST endpoint where we can request the cash flows of a given mortgage loan identified by it's identifier.  

Please use a framework of your liking, for instance spring boot. Bonus points will be awarded if a frontend is added where the cash flows of a loan can be retrieved.

## general conditions
Please write code as u usually do and would deliver it. You can commit it to the repo in a pull request of your own.

I understand the information regarding the construction of cash flows might be confusing. The exact correct implementation of that is not the main concern here (we are not testing your financial modeling skills) but feel free to ask me any questions if you want more information.

## good luck!!

