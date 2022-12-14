package com.example.data.di

/**
Di 설명

Dependency Injection
어떤 클래스는 다른 클래스에 대한 참조가 필요한 경우가 있다.
예를 들어, Car 클래스는 Engine 클래스 참조가 필요할 것이다.
이 때 Car 가 Engine 에 의존하고 있다고 말하고, Engine 을 Car 의 종속 항목 (디펜던시) 이라고 한다.

특정 클래스가 자신이 의존하고 있는 객체를 얻는 방법은 3가지가 있다. (Car 와 Engine 예제 활용)

1. Car 클래스 안에서 Engine 인스턴스를 생성하여 초기화한다.
2. 다른 곳에서 객체를 가져온다. Android 로 치면 Context, getSystemService() 등에 해당한다.
3. 객체를 파라미터로 제공받는다. Car 의 생성자가 Engine 을 파라미터로 받는다.
세 번째 방법이 바로 Dependency Injection 기법 중 하나이다.

DI 의 이점
1. 의존성 분리
클래스가 더이상 디펜던시의 생성 (인스턴스화) 에 관여하지 않기 때문에, 종속 항목이 변경되어도
(생성자 변경 등) 영향을 받지 않고 유연하게 동작한다. 즉, 리팩토링이 편리해진 것이다.

2. 클래스 재사용성 증가
의존하는 객체의 구현을 쉽게 갈아끼울 수 있다. 서브 타입 등 다양한 구현을 수용할 수 있고,
때문에 다양한 곳에서 클래스를 재사용할 수 있다.

3. 테스트 편의성
의존성이 분리되어, 테스트 시 다양한 구현을 전달하여 여러 시나리오를 검증해볼 수 있다.
(즉, Mocking 이 쉬워 진다 : Test Double 이 가능해졌다)


 * */