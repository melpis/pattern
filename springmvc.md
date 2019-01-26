DispatcherServlet
-----------------
* 흐름

![](흐름.PNG)

1. HandlerMapping
    > URL과 요청 정보 기준으로 어떤 핸들러 오브젝트, 즉 컨트롤러를 사용할것인지 결정하는 로직을 담당한다.
2. HandlerAdapter
    > 핸들러 매핑으로 선택한 컨트롤러/핸드러를 'DispatcherServlet'이 호출할때 사용하는 어댑터 이다.
    > 어노테이션 기반에 핸들러 어댑터에서는 리턴 타입 파라미터 사용이 자유롭다 위 맵핑에서는 클래스나 메소드명 정도만 가지고 있고
    > 실제 실행은 여기서 !!
3. HandlerExceptionResolver
4. ViewResolver
5. LocaleResolver
6. ThemeResolver
7. RequestToViewNameTranslator


DispatcherServlet 
> doService > doDispatch

processedRequest = checkMultipart(request);
				multipartRequestParsed = (processedRequest != request);

				// Determine handler for the current request.
				// url과 맵핑 되는 메소드 찾기
				HandlerExecutionChain mappedHandler = getHandler(processedRequest);
				
				if (mappedHandler == null) {
					noHandlerFound(processedRequest, response);
					return;
				}

				// Determine handler adapter for the current request.
				HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());
                if (!mappedHandler.applyPreHandle(processedRequest, response)) {
                            return;
                }
			
				// Actually invoke the handler.
				// 실제 실행 코드
				mv = ha.handle(processedRequest, response, mappedHandler.getHandler());

				applyDefaultViewName(processedRequest, mv);
				mappedHandler.applyPostHandle(processedRequest, response, mv);
				
				processDispatchResult(processedRequest, response, mappedHandler, mv, dispatchException);