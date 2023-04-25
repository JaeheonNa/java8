package com.study.java8;

/* Metaspace
    JVM의 여러 메모리 영역 중
    PermGen 메모리 영역이 사라지고,
    Metaspace 영역이 생김.

    ** PermGen 영역
        클래스 메타데이터를 담는 곳.
        Heap 영역에 속함.
        기본값으로 제한된 크기를 가짐.

    ** Metaspace 영역
        클래스 메타데이터를 담는 곳.
        Native 메모리 영역.
        Java8부터는 PermGen 관련 java 옵션 무시.

    ** 클래스 메타데이터
        클래스 명, 클래스 내 static 필드 등.

    ** java8 이전 heap : eden - oldGen - permGen
        객체가 젊을 수록 eden, 늙을수록 permGen으로 넘어감.
        GC는 eden이 꽉차면 eden 정리 시작, 정리 안 된 애들은 oldGen으로 넘김.
        oldGen이 꽉차면 oldGen 정리 시작, 정리 안 된 애들은 permGen으로 넘김.
        근데 permGen은 작은, 제한된 크기를 가졌음.

    ** java8 heap: eden - oldGen
        이제 permGen이 꽉차면, heap 밖의 Metaspace로 넘김.
        Metaspace는 permGen보다 훨씬 큼. 컴퓨터의 RAM 메모리 전체(native memory)라고 보면 됨.
        따라서 Metaspace는 기본값으로 제한된 크기를 갖고 있지 않음. 필요한 만큼 계속 늘어남.
        Metaspace 최대 크기 설정 가능함.

 */

import java.util.Objects;

public class Class18_metaspace {
    public static void main(String[] args){
    }

}
