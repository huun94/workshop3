package workshop3;

import java.util.*;

public class OptimalPageReplacement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of page frames: ");
        int frameCount = sc.nextInt();
        int[] pageFrames = new int[frameCount];
        Arrays.fill(pageFrames, -1);

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

            for(int j=0; j<frameCount; j++) {
                if(pageFrames[j] == currentPage) {
                    isPageFault = false;
                    break;
                }
            }

            if(isPageFault) {
                pageFaults++;

                int farthestPageIndex = -1;
                int farthestPageFrameIndex = -1;

                for(int j=0; j<frameCount; j++) {
                    int nextPageIndex = getNextPageIndex(pages, i+1, pageFrames[j]);

                    if(nextPageIndex == -1) {
                        farthestPageIndex = i+1;
                        farthestPageFrameIndex = j;
                        break;
                    }

                    if(nextPageIndex > farthestPageIndex) {
                        farthestPageIndex = nextPageIndex;
                        farthestPageFrameIndex = j;
                    }
                }

                pageFrames[farthestPageFrameIndex] = currentPage;
            }

            System.out.println("Page Frames: " + Arrays.toString(pageFrames));
        }

        System.out.println("Number of page faults: " + pageFaults);
    }

    private static int getNextPageIndex(int[] pages, int startIndex, int page) {
        for(int i=startIndex; i<pages.length; i++) {
            if(pages[i] == page) {
                return i;
            }
        }

        return -1;
    }
}
