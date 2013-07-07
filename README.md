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

