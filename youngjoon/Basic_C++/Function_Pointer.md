
# Function pointer

함수를 가리키는 포인터, 

```cpp
반환형 (*포인터이름)(매개변수 타입);
```

으로 사용함. 


### 사용 이유

1. **콜백 함수**

```cpp
bool compare(int a, int b) { return a > b; }
std::sort(arr, arr + n, compare);  // compare는 함수 포인터
```
위 케이스를 보면 알 수 있듯이, 함수를 파라미터로 쓸 수 있음.

2. **전략 패턴** 

```cpp
typedef int (*Strategy)(int, int);

int add(int a, int b) { return a + b; }
int mul(int a, int b) { return a * b; }

void execute(int a, int b, Strategy s) {
    cout << "Result: " << s(a, b) << endl;
}

execute(3, 4, add);  // 결과: 7
execute(3, 4, mul);  // 결과: 12

```
위 케이스처럼, 실행 시점에 알고리즘을 선택하게 할 수도 있음. 

3. **테이블 기반 명령 분기**

```cpp
void cmdA() { cout << "A\n"; }
void cmdB() { cout << "B\n"; }

void (*command_table[2])() = { cmdA, cmdB };

command_table[0]();  // "A"
```
switch 문 보다 빠르게 분기 처리가 가능함. 

switch 문은 런타임에 체크를 하는데, 

함수 포인터의 경우 분기 조건없이 바로 함수로 점프함. (어셈블리 점프)

근데 어차피 함수가 돌아가는 시간은 동일하기 때문에 큰 차이는 아닐 수 있음.

4. **플러그인 시스템**

```cpp
// 함수 포인터 타입 정의
typedef int (*PluginFunc)(int);
HMODULE dll = LoadLibraryA("plugin.dll");
PluginFunc plugin = (PluginFunc)GetProcAddress(dll, "doWork");


int result = plugin(42);
FreeLibrary(dll);
```

런타임에 로딩된 함수를 포인터로 호출 가능. 

사용하는 경우는 아래와 같음.

| 상황          | 이유                              |
| ----------- | ------------------------------- |
| 플러그인 구조     | 새 기능 추가 시 exe를 안 바꾸고 DLL만 교체 가능 |
| 런타임 로딩      | 성능 또는 라이선스 이유로 일부 기능만 조건부 로딩    |
| 리플렉션 비슷한 구조 | 함수 이름만 알고 실행 가능                 |


5. **C - style 연동**
   
C 라이브러리는 객체지향 기능이 없기 때문에,
콜백(callback)을 넘길 때는 **"함수 포인터"**를 씀.

🧩 C에서는?
상태(state) 없이 함수만 전달할 수 있습니다.

void (*callback)(int) 같은 식으로 정적 함수 주소만 넘길 수 있음

객체나 캡처된 환경은 못 넘깁니다.

C는 this 포인터 같은 게 없기 때문에, 콜백 함수 내부에서 어떤 컨텍스트도 유지할 수 없음

🔍 그래서 C는 콜백을 어떻게 처리하냐?
"내가 함수 주소만 받아서,
나중에 그 함수를 직접 호출할게."

이 구조만 가능하므로:

콜백 타입은 함수 포인터

콜백은 글로벌 또는 static 함수만 사용 가능

상태를 전달하려면 void* user_data 같은 별도 데이터 포인터를 같이 전달해야 함