# ![diary](https://github.com/TaiGa02/EnglishDiary/assets/135023031/5a9719a3-100b-40f8-b32f-310a85d71e11)  EnglishDiary 英語日記
※2023年11月23日現在google play storeに申請を中断<br>
リリースをするために必要なクローズドテストの要件の20人以上のテスターに14日以上連続で使用してもらうハードルが今は高いため、中断中


<br>


<img width="655" alt="スクリーンショット 2023-11-23 035831" src="https://github.com/TaiGa02/EnglishDiary/assets/135023031/f665aef1-0ada-484d-ac82-006573acfdc5">

参照
https://support.google.com/googleplay/android-developer/answer/14151465#production&zippy=%2C%E3%83%91%E3%83%BC%E3%83%88-%E3%82%AF%E3%83%AD%E3%83%BC%E3%82%BA%E3%83%89-%E3%83%86%E3%82%B9%E3%83%88%E3%81%AB%E9%96%A2%E3%81%99%E3%82%8B%E6%83%85%E5%A0%B1%E3%81%AE%E6%8F%90%E4%BE%9B%2C%E8%A3%BD%E5%93%81%E7%89%88%E3%81%B8%E3%81%AE%E3%82%A2%E3%82%AF%E3%82%BB%E3%82%B9%E3%82%92%E7%94%B3%E8%AB%8B%E3%81%99%E3%82%8B%E3%81%AB%E3%81%AF%E3%83%86%E3%82%B9%E3%82%BF%E3%83%BC%E3%81%8C-%E6%97%A5%E9%96%93%E4%BB%A5%E4%B8%8A%E9%80%A3%E7%B6%9A%E3%81%A7%E3%82%AA%E3%83%97%E3%83%88%E3%82%A4%E3%83%B3%E3%81%99%E3%82%8B%E5%BF%85%E8%A6%81%E3%81%8C%E3%81%82%E3%82%8B%E3%81%A8%E3%81%AE%E3%81%93%E3%81%A8%E3%81%A7%E3%81%99%E3%81%8C%E3%81%93%E3%82%8C%E3%81%AF%E3%81%A9%E3%81%86%E3%81%84%E3%81%86%E6%84%8F%E5%91%B3%E3%81%A7%E3%81%99%E3%81%8B

## <アプリの試し方>

- googleグループに参加
- url: https://groups.google.com/my-groups
- 参加後記載されているurlからAndroidのgoogle playを使用しご利用可能となっています。
- url: https://play.google.com/store/apps/details?id=com.Ishihara.englishdiary


## <アプリの動作映像>

https://github.com/TaiGa02/EnglishDiary/assets/135023031/b6224153-d896-432d-946d-c7c7074f83ca


## <アプリの概要>

- 英語日記の作成が可能
- 毎日の日記がカレンダーに保存される
- 分からない単語や表現などはすぐに検索可能
- 音読数も管理

## <使用した技術等>

言語 - Kotlin

SDK - Android Studio

データベース - Realm

外部API - Cloud Translation API

開発期間 - 3か月(言語の学習を含む)


## <英語日記の説明>

### 1. 日記を記録する。

 1.メイン画面のWrite Diaryボタンを押すことでページが日記を書くページに遷移

![diary1](https://github.com/TaiGa02/EnglishDiary/assets/135023031/295ebf32-cd51-49ed-ac3e-5067aafd5031)
<br>
 2.ユーザーに日記を書いてもらう

![diary2](https://github.com/TaiGa02/EnglishDiary/assets/135023031/6484f9d5-f622-4cb3-8c5b-02db4fb4440d)
<br>
 3.分からない表現や単語があれば右上のイメージボタンを押し辞書機能を開く

![diary3](https://github.com/TaiGa02/EnglishDiary/assets/135023031/0aa80f87-561d-4929-9819-9f5a3abb9f68)
![diary4](https://github.com/TaiGa02/EnglishDiary/assets/135023031/90e2848b-8755-45cb-9fac-fd9dcc35f7eb)
<br>
 4.自分自身の英語の獲得のために音読がおすすめ　->何回その日記を読んだかカウントできる

![diary5](https://github.com/TaiGa02/EnglishDiary/assets/135023031/d5dcc306-d649-43d9-8dab-69ba5b92f60b)
<br>
 5.STOREボタンを押すことで保存の確認ページへと遷移

![diary6](https://github.com/TaiGa02/EnglishDiary/assets/135023031/424bb68b-58e4-42a4-b8fa-021d52a42301)
<br>

### 2. 日記をカレンダーに保存する。

 1.保存確認ページに遷移するとそこで日記の確認とその日のタイトルをつける。

![diary7](https://github.com/TaiGa02/EnglishDiary/assets/135023031/6bbcdb2a-19bc-4d94-9d8f-57559c266853)
<br>
 2.もし見返して書き直したかったらBackボタンで前のページに戻る
<br>
 3.タイトルが決まれば記入し、いよいよSTOREボタンを押し、保存する。
<br>
 4.保存されるとカレンダーに保存された日記の日付の箇所にハイライトされる。

![Screenshot_20231119_000415](https://github.com/TaiGa02/EnglishDiary/assets/135023031/d6fc7e2a-f463-4867-800a-8ae1e4ee20f3)
![diary8](https://github.com/TaiGa02/EnglishDiary/assets/135023031/d4c73efc-2d4c-400b-a934-6faefb28c9d1)


### 3. 日記を確認する。

1. カレンダーの日にちを押すと保存されている日記のページに遷移
 <br>
 2. そこで気が向いたときに音読のカウントを増やして反映させられる。
 <br>

### 4. 書き直し

1. もし、その日に書いた日記に書き足したり変更を加えたい場合はメイン画面のWrite Diaryを押すとその日の日記が書かれたままになっている。（別の日の日記には手を付けることはできない。）
2. 再び何かを書き足したりする。
 
![diary9](https://github.com/TaiGa02/EnglishDiary/assets/135023031/0630af60-663f-428f-bb15-8e6aa728345d)
   <br>
 3. 同じ手順を踏みカレンダーに上書きすることが出来る。

![diary10](https://github.com/TaiGa02/EnglishDiary/assets/135023031/edc2e322-ab46-4210-bb13-c302caaf82f8)
![diary11](https://github.com/TaiGa02/EnglishDiary/assets/135023031/6c4d0664-4301-42f9-86b3-29ef553b7af5)

## <特に苦労した点>

カレンダーにデータを渡し、ハイライト及び詳細を見る機能の導入時

    Log機能を使いどのようなデータが途中でインプットされたのか追って上手く処理が渡るようにした
    // デバッグログを追加して保存されたデータが正しいか確認
    Log.d("StoreCheck", "Diary saved - Title: $title, Diary: $diary, Date: $date")
    Realmにはyyyy-MM-ddのフォーマットで保存している一方でユーザーに見せているのはMMMM yyyyもしくはdd MMMMだったためデータの判別に工夫が必要だった

APIの導入時

    APIの導入後ビルド自体ができなくなった
    エラーを調査し
    packagingOptions {
         exclude 'META-INF/INDEX.LIST' // 重複するファイルを除外
         exclude 'META-INF/DEPENDENCIES'
     }
     をgradle(app)に置くことでビルド時に重複するファイルについて除外した


