# atcoder-cli-template-kotlin

ReaderとWriterが付属している  
atcoder-cliのKotlin用テンプレート

## 使い方

### サンプルケースをテスト
```
./gradlew sampleJudge
```

### 提出
```
./gradlew submit
```
または
```
./gradlew s
```

### 提出 (サンプルケースのチェックなし)

```
./gradlew forceSubmit
```

## ライブラリについて

### 適用方法

#### kotlinx.collections.immutable

手動操作は不要です

#### ac-library-java

1. [こちら](https://github.com/ocha98/ac-library-java/releases)から最新の`ac_library23.jar`をダウンロード
2. `acc config-dir`を実行してatcoder-cliの設定ディレクトリの場所を確認
3. 確認したディレクトリ内に`atcoder-libs`ディレクトリを作成
4. ダウンロードしたライブラリを3のディレクトリに配置

### おまけ

#### ac-library-javaにソースファイルを設定する

1. [こちら](https://github.com/ocha98/ac-library-java/releases)から最新の`ac-library-java.zip`をダウンロード
2. ダウンロードしたzipファイルを解凍
3. 解凍したディレクトリ内の`src`ディレクトリにアクセス
4. `src`内のすべてのアイテムを含むzipファイルを作成
5. 作成したzipファイルの名前を`ac_library23-sources.jar`に変更
6. `ac_library23.jar`と同じディレクトリに配置


### クレジット
- [ac-library](https://github.com/atcoder/ac-library) - 作者 [AtCoder Inc.](https://github.com/atcoder)  
  ライセンス [Creative Commons Zero v1.0 Universal](https://github.com/atcoder/ac-library/blob/master/LICENSE)


- [AtCoderLibraryForJava](https://github.com/NASU41/AtCoderLibraryForJava) - 作者 [NASU41](https://github.com/NASU41)  
  ライセンス [Creative Commons Zero v1.0 Universal](https://github.com/NASU41/AtCoderLibraryForJava/blob/master/LICENSE)


- [ac-library-java](https://github.com/ocha98/ac-library-java) - 作者 [shinnshinn](https://github.com/ocha98)  
  ライセンス [Creative Commons Zero v1.0 Universal](https://github.com/ocha98/ac-library-java/blob/main/LICENSE)


- [kotlinx.collections.immutable](https://github.com/Kotlin/kotlinx.collections.immutable) - 作者 [Kotlin](https://github.com/Kotlin)  
  ライセンス [Apache License 2.0](https://github.com/Kotlin/kotlinx.collections.immutable/blob/master/LICENSE.txt)