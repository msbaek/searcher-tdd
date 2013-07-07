# 문제

문자열에서 원하는 단어가 있는지 확인해서, 원하는 단어가 있는 경우 그 단어를 강조하기 위해 단어의 앞뒤에 중괄호{}를 해서 출력한다.
단, 단어의 앞이나 뒤에 숫자/밑줄(_)/알파벳이 나오면 다른 단어라고 판단하고 출력하지 않는다.

예. 원하는 단어가 flex인 경우 입/출력은 아래와 같다.

| 입력   | 출력           |
| ----- | ------------- |
| flex? | {flex}? |
| (flex) | ({flex}) |
| reflex | |
| yes, flex1 | |
| no, flexible | |
| no a flex | no a {flex} |

# 1. 첫번째 테스트 추가

searcher.SearcherTest 클래스를 추가하고, 첫번째 테스트 메소드 foo를 추가한다. 이를 실행하여 테스트 환경이 제대로 동작함을 확인한다.

# 2. 첫번째 의미있는 테스트 추가

가장 단순하면서 흥미있고, [원하는 기능의 수준에 많이 떨어지는(degenerate)](http://dictionary.reference.com/browse/degenerate) 테스트를 추가한다.

이러한 순서로 테스트를 추가할 때 지속적으로 production 코드를 generalize할 수 있고 저절로 알고리즘이 나타난다.

## 2.1 Add Failing Test

제일 쉽게 생각할 수 있는 것은 statement와 keyword가 모두 null인 경우이다. 이때 ""를 반환하는 테스트를 추가한다.

## 2.2 Make it pass

가장 적은 코딩으로 테스트가 성공하도록 한다(play a little golf라고 밥 아저씨가 언급한).

# 3. 다른 Degenerate 테스트 케이스 추가

(statement, keyword)가 (null, null)인 경우 외에 유사한 (null, ""), ("", null), ("", "")의 경우도 테스트를 추가한다.

# 4. Refactoring

테스트 메소드 많은 중복이 보인다. 메소드 추출(extract method)을 통해 중복을 메소드 호출로 치환한다.

## 4.1 중복 제거 사전 준비

메소드로 추출할 영역들에서 추출할 메소드의 파라미터로 처리해야할 서로 다른 부분들이 있다. 이를 변수로 추출하여 사전 준비를 한다.
