##QUESTIONS
###Q1:
How to find duplicate element in array of 100 where all elements are in range from 0-99?
###Q2:
Что такое векторы признаков?
###Q3:
Объясните шаги при создании дерева решений.
###Q4:
Что делать, если данных не хватает или они плохого качества?
###Q5:
Что такое логистическая регрессия?
###Q6:
Как отделить новые признаки от уже существующих?
###Q7:
Дано 100-этажное здание. Если яйцо сбросить с высоты N-го этажа (или с большей высоты), оно разобьется. Если его бросить с любого меньшего этажа, оно не разобьется. У вас есть два яйца. Найдите N за минимальное количество бросков.
###Q8:
Как бы вы использовали Python в работе с очень большим файлом чисел, разделенных табуляцией, для подсчета частоты каждого числа?
###Q9:
Если вы знаете, что у вашего друга двое детей и что по крайней мере один из них мальчик, какова вероятность, что другой тоже мальчик?
###Q10
У вас есть 70 красных шариков. Соотношение зеленых и красных шариков составляет 2 к 7, сколько тогда зеленых?

##TASKS
###Task 1:
Your company is running the test that is designed to compare two different versions of company's website.

Version A is shown to 60% of users, while version B is shown to remaining 40% of users.
The test shows that 8% of users who are presented with version A sign up for a company's services compared to 4% of users
who are presented with version B.

If a user signs up for a company's services, what is the probability(%) that he/she was presented with version A of website?

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

