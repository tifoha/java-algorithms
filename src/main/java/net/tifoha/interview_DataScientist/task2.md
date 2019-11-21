###Task 2:
Implement the desired_marketing_expenditure function which returns the required amount of money
that needs to be invested in a new marketing campaign to sell the desired number of units.

Use the data from previous marketing campaigns to evaluate, how the number of units sold grows **linearly** 
as the amount of money invested increases.

For example, for the desired number of 60000 sold and previous campaign data from the table below,
the function should return the float 250000

PREVIOUS CAMPAIGNS

| CAMPAIGN | MARKETING EXPEDITURE | UNITS SOLD |
|:--------:|:--------------------:|: ---------:|
| #1       | $300000              | 60000      |
| #2       | $200000              | 50000      |
| #3       | $400000              | 90000      |
| #4       | $300000              | 80000      |
| #5       | $100000              | 30000      |
```
import numpy as np
 from sklearn import linear_model
 
 class MarketingCosts:
 
     # param marketing_expenditure list. Expenditure for each previous campaign.
     # param units_sold list. The number of units sold for each previous campaign.
     # param desired_units_sold int. Target number of units to sell in the new campaign.
     # returns float. Required amount of money to be invested.
     @staticmethod
     def desired_marketing_expenditure(marketing_expenditure, units_sold, desired_units_sold):
         return None
 
 #For example, with the parameters below the function should return 250000.0.
 print(MarketingCosts.desired_marketing_expenditure(
     [300000, 200000, 400000, 300000, 100000],
     [60000, 50000, 90000, 80000, 30000],
     60000))
```

