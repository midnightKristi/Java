// Applying binary search algorithm to the sorted array
// This program determines air mileage from New York to other cites.

import java.util.*;

public class SearchMileage {
    // Names of international cities
    // this array is sorted
    private static final String[] CITY_NAMES = {"acapulco",     "amsterdam",    "antigua",
                                                "aruba",        "athens",       "barbados",
                                                "bermuda",      "bogota",       "brussels",
                                                "buenos aires", "caracas",      "copenhagen",
                                                "curacao",      "frankfurt",    "geneva",
                                                "glasgow",      "hamburg",      "kingston",
                                                "lima",         "lisbon",       "london",
                                                "madrid",       "manchester",   "mexico city",
                                                "milan",        "nassau",       "oslo",
                                                "paris",        "reykjavik",    "rio de janeiro",
                                                "rome",         "san juan",     "santo domingo",
                                                "st. croix",    "tel aviv",     "zurich"};
    // Distances from New York to other cities
    private static final int[] DISTANCES = { 2260, 3639, 1783, 1963, 4927, 2100, 771, 2487, 3662,
                                             5302, 2123, 3849, 1993, 3851, 3859, 3211, 3806, 1583,
                                             3651, 3366, 3456, 3588, 3336, 2086, 4004, 1101, 3671,
                                             3628, 2600, 4816, 4280, 1609, 1560, 1680, 5672, 3926};

    public static void main(String[] args){
        // Display initial message
        System.out.println( "__________________________________________\n" +
                            "This program finds the air mileage between\n" +
                            "New York and major international cities.\n" +
                            "__________________________________________\n");

        Scanner scan = new Scanner(System.in);
        // Prompt for city name
        System.out.println("Enter city name: ");
        // getting the user input
        String cityName = scan.nextLine().trim();

        // Use binary search to locate name in CITY_NAMES array
        int i = binarySearch(CITY_NAMES, cityName.toLowerCase());

        // If city was found in the array, display the distance from NY to the chosen city
        if(cityName.equalsIgnoreCase(CITY_NAMES[i]))
            System.out.println(cityName + " is " + DISTANCES[i] + " miles from New York City.");
        else
            System.out.println(cityName + " wasn't found.");
    } // end of main


    /////////////////////////////////////////////////////////////////////////////
    // NAME: binarySearch
    // BEHAVIOR: Uses the binary search algorithm to locate key in the array a.
    //           Returns the index of key if it is found in a.
    // PARAMETERS: array, key
    // RETURNS: index
    /////////////////////////////////////////////////////////////////////////////
    private static int binarySearch(String[] a, String key){
        int low = 0;
        int high = a.length - 1;

        while(low < high){
            // calculate middle element of the array
            int mid = (low + high) / 2;
            // if key smaller than the middle element, the search will limit to the first half of the array
            if (a[mid].compareTo(key) < 0)
                low = mid + 1;
            else
                // otherwise, the search will go to the second half
                high = mid;
        }
        return low;
    }

} // end of SearchMileage
