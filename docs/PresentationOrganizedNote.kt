package com.example.tasam_android

/**
Presentation

결국 Presentation 코드들을 확인해보면 다음과 같은 구성인 것을 확인할 수 있다.
- NavHostActivity -> BaseActivity를 상속받으며 각 Fragment를 연결한다.
                   + 연결의 경우 Navigation/navigation_graph.xml 에서 설정한 후 사용한다.
- Common -> Base, Di, Util, widget : Base 세팅, 의존성, 부가적인 기능
- 각 Page(Ex. Home) -> HomeFragment, HomeViewModel, HomeNavigationAction, HomeActionHandler
                     + RecyclerView 와 같은 기능이 있어 adpater를 사용해야한다면 TodoAdapter, TodoViewHolder 사용
                     + 레이아웃에 내가 원하는 메서드를 만들고 싶다면 HomeBidingAdaters 사용

그렇다면 Presentation 이 어떤 로직으로 흘러가는지 알아보면 다음과 같다.
진행방향 ->
NavHostActivity : BaseActivity -> 각 Fragment(EX. Home) == HomeFragment : BaseFragment <-> HomeViewModel : BaseViewModel <-> (HomeActionHandler : Layout 이벤트 처리 + HomeNavigationAction : 뷰 이동이 있을 때 사용)
Adapter 가 있는 경우 TodoAdapter <-> TodoViewHolder <-> HomeFragment : BaseFragment <-> HomeViewModel : BaseViewModel

한줄 평: 이게 객체지향이구나.. 싶다.. 생각보다 구조가 엄청 어렵진 않지만 Base 세팅과 사용해보지 못한 함수들이 꽤 있어 어려웠다.
        현재까지는 구조를 초점으로 공부하고 있어서 구조가 완전히 익혀진 후 어떤 함수인지 사용해보면서 공부해야할 것 같다.

 * */