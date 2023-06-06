package workshop3;


import java.util.*;

public class LRUPageReplacement {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.print("Enter the number of page frames: ");
int frameCount = sc.nextInt();
Queue<Integer> pageFrames = new LinkedList<>();

    System.out.print("Enter the number of pages: ");
    int pageCount = sc.nextInt();
    int[] pages = new int[pageCount];

    System.out.print("Enter the page reference string: ");
    for(int i=0; i<pageCount; i++) {
        pages[i] = sc.nextInt();
    }

    int pageFaults = 0;
    for(int i=0; i<pageCount; i++) {
        int currentPage = pages[i];
        boolean isPageFault = true;

        // Kiểm tra xem trang hiện tại đã có trong frame hay chưa
        if(pageFrames.contains(currentPage)) {
            isPageFault = false;
            pageFrames.remove(currentPage);
        }

        // Nếu trang hiện tại chưa có trong frame
        if(isPageFault) {
            pageFaults++;

            // Xóa trang ở đầu queue (trang được sử dụng lâu nhất)
            if(pageFrames.size() == frameCount) {
                pageFrames.remove();
            }
        }

        // Thêm trang hiện tại vào cuối queue
        pageFrames.add(currentPage);

        System.out.println("Page Frames: " + pageFrames);
    }

    System.out.println("Number of page faults: " + pageFaults);
}
}