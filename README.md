# 항해+

## 1주차 과제 CI/CD 구성

### Github Actions
#### 장점
* 쉽게 무료로 CI/CD를 구축할 수 있다.

#### 단점
* Detail한 설정이 아쉬웠다.
    * ECR에 이미지를 업로드하는 것 까지는 쉽게 구축할 수 있지만.
      운영에 배포할 때 롤백 & 이전 환경(stage)에서 성공적으로 배포된 이미지 선택 등
      원하는 이미지 태그를 선택해서 배포하는 것이 어려웠다.


### Infra Architecture
#### - **AS-IS**
![img1.png](resource/img1.png)

#### - **TO-BE**
![img.png](resource/img.png)

### 성능 개선
#### Gradle caching
```yaml
- name: Cache gradle modules
      uses: actions/cache@v3
      with:
          path: |
              ~/.gradle/caches
              ~/.gradle/wrapper
          key: ${{ runner.os }}-gradle-${{ hashFiles('**/*.gradle*', '**/gradle-wrapper.properties') }}
          restore-keys: |
              ${{ runner.os }}-gradle-
```

#### Docker layer caching
```yaml
    - name: Cache docker layers
      uses: actions/cache@v2
      with:
          path: /tmp/.buildx-cache
          key: ${{ runner.os }}-buildx-${{ github.sha }}
          restore-keys: |
              ${{ runner.os }}-buildx-
```

* 의존성 및 도커 이미지 캐싱 : 4m 55s (35s↓)
이 부분도 아직 의존성과 필요한 도커 이미지가 적어서 그런 것으로 판단
