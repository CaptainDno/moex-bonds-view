This is very simple terminal based application to view some info on bonds traded on MOEX.  

Example output:
```
>>> load https://iss.moex.com/iss/engines/stock/markets/bonds/securities.xml
Загрузка успешно завершена
>>> show first 5
╔═ RU000A1032L0 ═════════════════════════════════════════════════════════════════════════════════════════════════╗
║ СберИОС 001Р-440R 7Г СШАМ ИНД                                                                                  ║
║  Номинал:   1000,000 RUB           Дата погашения: 02.06.2028                                                  ║
║┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅║
║  Текущая цена:  71,40% (   714,000 RUB)   ┆   Средняя цена:  71,40% (   714,000 RUB)                           ║
║  Доходность:     10,673%                  ┆   Доходность:     10,673%                                          ║
╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝
╔═ RU000A1089K2 ═════════════════════════════════════════════════════════════════════════════════════════════════╗
║ МКПАО ОК РУСАЛ БО-001P-07                                                                                      ║
║  Номинал:   1000,000 RUB           Дата погашения: 09.10.2026                                                  ║
║┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅║
║  Текущая цена:  95,15% (   951,489 RUB)   ┆   Средняя цена:  94,38% (   943,763 RUB)                           ║
║  Доходность:     11,576%                  ┆   Доходность:     12,151%                                          ║
╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝
╔═ RU000A102V51 ═════════════════════════════════════════════════════════════════════════════════════════════════╗
║ Нафтатранс плюс ООО БО-03                                                                                      ║
║  Номинал:   1000,000 RUB           Дата погашения: 20.02.2026                                                  ║
║┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅║
║  Текущая цена:  86,40% (   864,000 RUB)   ┆   Средняя цена:  85,94% (   859,400 RUB)                           ║
║  Доходность:     46,163%                  ┆   Доходность:     47,586%                                          ║
╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝
╔═ RU000A103PG3 ═════════════════════════════════════════════════════════════════════════════════════════════════╗
║ Славнефть БО 002Р-02                                                                                           ║
║  Номинал:   1000,000 RUB           Дата погашения: 02.09.2031                                                  ║
║┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅║
║ Нет информации о доходности                                                                                    ║
╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝
╔═ RU000A1098F3 ═════════════════════════════════════════════════════════════════════════════════════════════════╗
║ АФК Система БО 001Р-31                                                                                         ║
║  Номинал:   1000,000 RUB           Дата погашения: 08.11.2028                                                  ║
║┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅║
║  Текущая цена:  81,00% (   810,000 RUB)   ┆   Средняя цена:  80,85% (   808,500 RUB)                           ║
║  Доходность:      6,231%                  ┆   Доходность:      6,292%                                          ║
╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝

>>> 

```


### Supported commands

`load <URL>` - load and parse file from URL

`reset` - restore original ordering and remove all applied filters

`order_by <maturity_date|yield_current|yield_avg> <asc|desc>` - self-explanatory. 
Average here means weighted average price for current trading session

`filter yield <known|unknown>` or 

`filter yield >= <double>` - leave only bonds with average or current yield greater than provided value

`filter maturity_date <before|after> <date in dd:mm:yyyy format>` - filter by date

`show <first|last> <integer> [skip <integer>]` - show information on first or last bonds in the list with current ordering. Skip may be used for pagination.