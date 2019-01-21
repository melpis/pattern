DispatcherServlet
-----------------
* 흐름

![](흐름.PNG)

1. HandlerMapping
    > URL과 요청 정보 기준으로 어떤 핸들러 오브젝트, 즉 컨트롤러를 사용할것인지  결정하는 로직을 담당한다.
2. HandlerAdapter
    > 핸들러 매핑으로 선택한 컨트롤러/핸드러를 'DispatcherServlet'이 호출할때 사용하는 어댑터 이다.
3. HandlerExceptionResolver
4. ViewResolver
5. LocaleResolver
6. ThemeResolver
7. RequestToViewNameTranslator
