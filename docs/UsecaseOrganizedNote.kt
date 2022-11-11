package com.example.domain.usecase

/**
Use case 란?
In general
Use case란 만들고 있는 서비스를 사용하는 유저가 이 서비스(또는 시스템)을 통해 하고자 하는 것을 말한다고 한다.
예를 들어, ‘음식점’이라는 서비스가 있다고 가정해보자.
음식점에서 손님은 ‘음식 주문’을 할 수도 있고 ‘음식 포장’을 할수도 있고 ‘가격 지불’을 할 수도 있을거다.
이때 이런 ‘음식 주문’, ‘음식 포장’, ‘가격 지불’ 등등이 ‘음식점’이라는 시스템에 사용자가 요청할 수 있는 Use case가 되는거다! :)
즉 최소 요청 단위이다!

In clean architecture
UseCase는 Domain Layer에 속해있고 Domain Layer는 안드로이드에 의존하지 않은 순수한 Java / Kotlin 모듈이다.

Use case를 쓰는 이유?
Avoid “God” Presenters/ViewModels
만약 관심사 분리를 제대로 하지 않는다면 어떻게 될까? 모든걸 다 아는 God Object가 될거다!
use case 가 없다면 viewMode 이 Repository 등 모든 것에 의존하게 된다고 한다.
use case 가 있다면 의존도가 use case로 옮겨가 data logic이 이동에 더 가벼운 viewModel을 갖게 된다.
만약에 비슷한 비슷한 역할을 하는 viewModel이 있다면 이미 구현한 use case를 재사용하면 된다.

“Useless Use Cases”
유즈케이스 코드에는 레포지토리 메소드 호출 코드만 있다. (usecase 폴더 참고)
이럴 경우에도 굳이 써야하나? 라는 생각이 들었다. 답은? Yes!

1. Consistency
첫번째로 일관성입니다. 일부 viewmodel은 유즈케이스를 호출하고 다른 ViewModel은 레포지토리를 직접 호출하는 것이 좋지않을까? 라고 생각하겠지만,
그렇게 되면 코드를 처음 본 사람이나 신입에게 이해하기 어려울 수 있을거다.

2. Protect the code from future changes
두 번째로는 미래 있을 변화에 미리 보호하자는 것이다.
클린 아키텍쳐의 중요한 목적 중 하나는 요구사항이 변경될 때 쉽게 적응할 수 있는 코드 베이스를 제공하는 것이다.
이는 즉, 변경해야 하는 코드의 양이 최소여야 함을 의미한다.

3. The “Screaming Architecture”
What is your app doing? -> 니 앱 그래서 머하는 앤데?
Screaming architecture는 Robert C. Martin이 제안한 구조라고 한다.
소프트웨어의 구조가 소리치듯이 구조만 봐도 어떤 서비스 인지 알 수 있어야 한다는 의미를 가지고 있다고 한다.
앞에서도 말했듯 Screaming architecture의 핵심은 구조만 보고도 소프트웨어가 무엇인지 알 수 있도록 명확해야 한다는 것이고, 때문에 use case를 써서 확실한 구조를 가져가자는 것이라고 한다!


참고 : https://mashup-android.vercel.app/mashup-11th/heejin/useCase/useCase/

 * */