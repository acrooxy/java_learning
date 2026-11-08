# QuickTasks

Prosta lista zadań w konsoli, napisana w Javie bez dodatkowych bibliotek.
Dodaje zadania, pokazuje postęp, oznacza wykonane zadania i usuwa wpisy.
Dane zapisuje w pliku `tasks.txt`, więc pozostają po zamknięciu programu.

## Uruchomienie

Wymagany JDK 17 lub nowszy. Otwórz terminal w tym folderze:

```sh
java QuickTasks.java add "Nauczyć się kolekcji w Javie"
java QuickTasks.java add "Zrobić mały projekt"
java QuickTasks.java list
java QuickTasks.java done 1
java QuickTasks.java remove 2
```

Przykładowy wynik `list` po oznaczeniu pierwszego zadania:

```text
1. [x] Nauczyć się kolekcji w Javie
2. [ ] Zrobić mały projekt
Completed: 1/2
```

Numery zadań odpowiadają aktualnej liście i zmieniają się po usunięciu wpisu.
`done` można powtarzać bez zmiany pozostałych zadań. Błędne argumenty zwracają
kod wyjścia 1. Uszkodzony plik danych nie jest nadpisywany.

Alternatywnie: `javac QuickTasks.java`, a następnie `java QuickTasks list`.
Inny plik danych: `java -Dquicktasks.file=my-tasks.txt QuickTasks.java list`.

Projekt pokazuje: rekordy, kolekcje, switch, strumienie, obsługę błędów i zapis plików.

## Testy

```sh
javac -encoding UTF-8 QuickTasks.java QuickTasksTest.java
java QuickTasksTest
```

Testy używają osobnego katalogu tymczasowego. Sprawdzają trwałość danych,
polskie znaki, usuwanie, powtarzanie `done`, błędne argumenty i ochronę
uszkodzonego pliku. Nie zmieniają Twojego `tasks.txt`.

GitHub Actions uruchamia te same testy na Javie 17 przy zmianach w projekcie.

## Jak to działa

Każde zadanie jest rekordem `Task` z treścią i stanem wykonania. Polecenie
wczytuje listę z dysku, wykonuje zmianę, a następnie zapisuje całość przez
plik tymczasowy. Format danych to stan `0` lub `1`, tabulator i treść zadania.
Program jest przeznaczony do używania przez jedną osobę w jednym terminalu.

