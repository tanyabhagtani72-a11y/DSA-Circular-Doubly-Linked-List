
import java.util.Scanner;

class Song {
    String title;
    Song next;
    Song prev;

    Song(String title) {
        this.title = title;
        this.next = null;
        this.prev = null;
    }
}

class CircularPlaylist {
    private Song head;
    private Song current;

    public void addSong(String title) {
        Song newSong = new Song(title);

        if (head == null) {
            head = newSong;
            newSong.next = newSong;
            newSong.prev = newSong;
            current = newSong;
        } else {
            Song tail = head.prev;

            tail.next = newSong;
            newSong.prev = tail;
            newSong.next = head;
            head.prev = newSong;
        }

        System.out.println("Song added: " + title);
    }

    public void deleteSong(String title) {
        if (head == null) {
            System.out.println("Playlist is empty.");
            return;
        }

        Song temp = head;

        do {
            if (temp.title.equalsIgnoreCase(title)) {
                if (temp.next == temp) {
                    head = null;
                    current = null;
                } else {
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;

                    if (temp == head) {
                        head = temp.next;
                    }

                    if (temp == current) {
                        current = temp.next;
                    }
                }

                System.out.println("Song deleted: " + title);
                return;
            }

            temp = temp.next;
        } while (temp != head);

        System.out.println("Song not found.");
    }

    public void displayPlaylist() {
        if (head == null) {
            System.out.println("Playlist is empty.");
            return;
        }

        Song temp = head;
        System.out.println("\nCircular Playlist:");

        do {
            System.out.print(temp.title + " <-> ");
            temp = temp.next;
        } while (temp != head);

        System.out.println("(back to " + head.title + ")");
    }

    public void nextSong() {
        if (current == null) {
            System.out.println("Playlist is empty.");
            return;
        }

        current = current.next;
        System.out.println("Now playing: " + current.title);
    }

    public void previousSong() {
        if (current == null) {
            System.out.println("Playlist is empty.");
            return;
        }

        current = current.prev;
        System.out.println("Now playing: " + current.title);
    }

    public void showCurrentSong() {
        if (current == null) {
            System.out.println("Playlist is empty.");
        } else {
            System.out.println("Current song: " + current.title);
        }
    }
}

public class CircularDoublyLinkedList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CircularPlaylist playlist = new CircularPlaylist();
        int choice = 0;

        do {
            System.out.println("\n===== CIRCULAR MUSIC PLAYER =====");
            System.out.println("1. Add Song");
            System.out.println("2. Delete Song");
            System.out.println("3. Display Playlist");
            System.out.println("4. Next Song");
            System.out.println("5. Previous Song");
            System.out.println("6. Show Current Song");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                sc.nextLine();
                continue;
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter song title: ");
                    String title = sc.nextLine().trim();

                    if (title.isEmpty()) {
                        System.out.println("Song title cannot be empty.");
                    } else {
                        playlist.addSong(title);
                    }
                    break;

                case 2:
                    System.out.print("Enter song title to delete: ");
                    String deleteTitle = sc.nextLine().trim();
                    playlist.deleteSong(deleteTitle);
                    break;

                case 3:
                    playlist.displayPlaylist();
                    break;

                case 4:
                    playlist.nextSong();
                    break;

                case 5:
                    playlist.previousSong();
                    break;

                case 6:
                    playlist.showCurrentSong();
                    break;

                case 7:
                    System.out.println("Music player closed.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 7);

        sc.close();
    }
}
