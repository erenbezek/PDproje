# PDproje

 Proje Adı: Real-Time Grammar-Based Syntax Highlighter with GUI



# Projenin Amaci:
Bu proje, kullanıcı tarafından yazılan Java kodlarını anlık olarak analiz eden ve

anahtar kelimeleri tanıyan, renklendiren, 

söz dizimsel olarak hatalı olup olmadığını belirleyen,

parantezlerin dengeli olup olmadığını kontrol eden

basit bir Java syntax highlighter oluşturmayı amaçlar.

# Yapılanlar:

CodeEditor.java: Kod yazma alanı ve canlı analiz sistemi

Lexer.java: Token sınıflandırması

TokenType.java: Anahtar kelimeler, parantezler, sayı gibi token türleri

Parser.java: Blok parantez kontrolü { }

Main.java: Uygulama giriş noktası

# Özellikler:

--Gerçek zamanlı analiz

--“if”, “while”, “int” gibi anahtar kelimelerin renklendirilmesi

--Süslü parantezlerin dengeli olup olmadığını kontrol eden Parser

--Kullanıcıya geçerli / geçersiz kod uyarısı verme

