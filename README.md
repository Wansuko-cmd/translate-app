# 文章変換アプリ

勉強会のネタ

# Hello World

1. アプリの起動
2. ファイル構成の説明
3. Hello Worldの文字を変えてみる(activity_main.xmlを変更する)
4. 動的に文章を変更してみる(ViewBindingを有効化)

# 猛虎弁変換機作成

1. 入力欄と出力欄を作成
2. ボタンを押したときに入力欄と出力欄の内容を同期する機能を追加
3. 猛虎弁に変換する機能を追加

# 猛虎弁を変換した履歴を表示する

1. Epoxyを導入
2. activity_main.xmlにRecyclerViewを設置(layoutManagerを忘れないように)
3. model_history_row.xmlを作成
4. Epoxyの設定ファイル(EpoxyConfig.java)を追加
5. EpoxyControllerを追加
6. Epoxyを用いてRecyclerViewに文字を表示
7. 変換した文字列を履歴として表示
8. 回転すると履歴がぶっ飛ぶことを確認
9. ViewModelを導入

# 英語翻訳機能を追加

1. 一度APIを叩いてみる
2. Viewに英語変換ボタンを追加
3. ネットワーク通信に必要な機能を追加