# atcoder-cli-templates

atcoder-cliのテンプレート置き場

## 前提要件

- [node.js](https://nodejs.org/)
- [online-judge-tools](https://github.com/kmyk/online-judge-tools)
- [atcoder-cli](https://github.com/Tatamo/atcoder-cli)

これらのツールが必要です  
まだインストールされていない場合は[こちら](https://qiita.com/Adaachill/items/3d4ddad56c5c2cc372cd)を参考にしてください

## 適用方法

1. このリポジトリから使いたい言語のテンプレートをダウンロードする
2. `acc config-dir`を実行してatcoder-cliの設定ディレクトリの場所を確認する
3. 1でダウンロードしたテンプレートを2で確認したディレクトリに置く
4. `acc config default-template {テンプレートフォルダ名}`を実行してこれをデフォルトのテンプレートにする (任意)

## 使い方

### コンテストスケルトンの生成
`acc new {コンテストid} --template {テンプレートフォルダ名}`

※ 手順4でデフォルトテンプレートを設定している場合は`--template {テンプレートフォルダ名}`の部分は不要です

※ コンテストidはコンテストページのURL末尾の部分です  
   例: `https://atcoder.jp/contests/abc347`の場合`abc347`がコンテストidです

### ジャッジ方法
それぞれのテンプレートフォルダにあるREADME.mdを参照してください