# PDproje

 Proje Adı:
Java Dilinde Sözdizimi Renklendirme ve Hata Denetimi Aracı

👨‍💻 Kullanılan Teknolojiler:
Java Swing (grafik arayüz)

Regex tabanlı Lexer (sözcük tanıma)

Basit Parser (parantez denetimi)

JTextPane + StyledDocument ile canlı renklendirme

🎯 Projenin Amacı:
Bu proje, kullanıcı tarafından yazılan Java kodlarını anlık olarak analiz eden ve:

Anahtar kelimeleri tanıyan ve renklendiren

Söz dizimsel olarak hatalı olup olmadığını belirleyen

Parantezlerin dengeli olup olmadığını kontrol eden

basit bir Java syntax highlighter oluşturmayı amaçlar.

🔧 Yapılanlar:
CodeEditor.java: Kod yazma alanı ve canlı analiz sistemi

Lexer.java: Token (sözcük) sınıflandırması

TokenType.java: Anahtar kelimeler, parantezler, sayı vs. gibi token türleri

Parser.java: Blok parantez kontrolü { }

Main.java: Uygulama giriş noktası

🚀 Özellikler:
Gerçek zamanlı analiz (yazarken kontrol)

“if”, “while”, “int” gibi anahtar kelimelerin renklendirilmesi

Süslü parantezlerin dengeli olup olmadığını kontrol eden Parser

Kullanıcıya geçerli / geçersiz kod uyarısı

