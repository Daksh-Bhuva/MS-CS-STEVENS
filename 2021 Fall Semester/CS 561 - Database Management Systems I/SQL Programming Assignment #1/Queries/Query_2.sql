with OCT as(
	select cust, prod, max(quant) as OCT_MAX
	from sales
	where month = 10 and year > 2017
	group by cust, prod
),	

OCT_FINAL as(
	select OCT.cust as CUSTOMER, OCT.prod as PRODUCT, OCT.OCT_MAX, S.date as OCT_DATE
	from OCT, sales as S
	where OCT.cust = S.cust and OCT.prod = S.prod and OCT.OCT_MAX = S.quant and S.month = 10 and S.year > 2017
),

NOV as(
	select cust, prod, min(quant) as NOV_MIN
	from sales
	where month = 11
	group by cust, prod
),	

NOV_FINAL as(
	select NOV.cust as CUSTOMER, NOV.prod as PRODUCT, NOV.NOV_MIN, S.date as NOV_DATE
	from NOV, sales as S
	where NOV.cust = S.cust and NOV.prod = S.prod and NOV.NOV_MIN = S.quant and S.month = 11
),

DECEM as(
	select cust, prod, min(quant) as DEC_MIN
	from sales
	where month = 12
	group by cust, prod
),

DECEM_FINAL as(
	select DECEM.cust as CUSTOMER, DECEM.prod as PRODUCT, DECEM.DEC_MIN, S.date as DEC_DATE
	from DECEM, sales as S
	where DECEM.cust = S.cust and DECEM.prod = S.prod and DECEM.DEC_MIN = S.quant and S.month = 12
)

select OCT_FINAL.CUSTOMER, OCT_FINAL.PRODUCT, OCT_FINAL.OCT_MAX, OCT_FINAL.OCT_DATE, 
	   NOV_FINAL.NOV_MIN, NOV_FINAL.NOV_DATE, DECEM_FINAL.DEC_MIN, DECEM_FINAL.DEC_DATE
from OCT_FINAL 
full outer join NOV_FINAL
using (CUSTOMER, PRODUCT)
full outer join DECEM_FINAL
using (CUSTOMER, PRODUCT)
order by CUSTOMER, PRODUCT
