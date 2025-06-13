package data.structures.algorithms;

import java.util.*;
import java.util.stream.Collectors;

public class TransactionLogsExampleAmazon {
    public static List<String> processLogs(List<String> logs, int threshold) {
        Map<String, Integer> transactionCount = new HashMap<>(logs.size());

        for (String log : logs) {
            String[] parts = log.split(" ");
            if (parts.length < 3) continue;

            String sender = parts[0];
            String receiver = parts[1];

            // Use compute for faster map operations
            transactionCount
                    .compute(sender, (k, v) -> v == null ? 1 : v + 1);

            if (!sender.equals(receiver)) {
                transactionCount
                        .compute(receiver, (k, v) -> v == null ? 1 : v + 1);
            }
        }

        return transactionCount.entrySet().stream()
                .filter(entry -> entry.getValue() >= threshold)
                .map(Map.Entry::getKey)
                .sorted(Comparator.comparingInt(Integer::parseInt))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> logs =
                Arrays.asList( "527771506 111186861 288101579", "507336886 451913864 466472568", "274673668 56172121 499043961",
                "507067818 451913864 408769398", "507336886 507067818 689764809", "274673668 831696632 284146974",
                "274673668 451913864 709332133", "451913864 56890051 199469834", "526393340 646024821 423406367",
                "615008389 274673668 790373061", "554316372 809815918 651837455", "507336886 615008389 260289772",
                "615008389 76731002 548804246", "656564087 906342698 873279663", "253313766 753290919 191103431",
                "274673668 76731002 596739043", "831696632 507067818 5251199", "154194267 658732874 720632769",
                "56890051 274673668 899126834", "637700023 793696384 508755746", "56890051 56172121 344802494",
                "451913864 507067818 527459789", "76731002 274673668 770874721", "864250034 864250034 168635902",
                "507336886 451913864 956650170", "507336886 507067818 803661727", "554316372 791817798 895876766",
                "131049042 376719871 162247213", "507067818 831696632 237530202", "811497682 63100538 776190400",
                "507067818 507336886 605094829", "274673668 76731002 452042686", "56172121 831696632 297433872",
                "864250034 615008389 143576841", "891331683 851362051 711809105", "507336886 274673668 349820788",
                "816891240 801259225 138398535", "321211429 80068912 660919222", "615008389 274673668 290212005",
                "855293372 451913864 916478910", "309581888 376679307 791661958", "56890051 56890051 502007115",
                "718869660 525695655 769164551", "611948363 86215482 309290718", "831696632 451913864 877960152",
                "507336886 615008389 865307502", "274555361 980027280 947904379", "445111193 87790742 681929261",
                "451913864 56172121 448927964", "309467897 808623872 469015606", "486270921 226543047 137565354",
                "831696632 451913864 801095976", "56890051 831696632 576771232", "639793930 181963137 966264614",
                "766959001 416586784 638991193", "698479963 391957985 595041229", "952789397 142779469 607572904",
                "507067818 76731002 743307706", "771097814 809043720 165539166", "56890051 507067818 484251131",
                "367840959 947130171 984439794", "56890051 76731002 352559311", "507336886 56172121 647305357",
                "507327697 988331486 554151953", "34798966 67233364 778515374", "507336886 507336886 125170296",
                "855293372 864250034 989122703", "451913864 76731002 696071989", "507067818 507067818 522748036",
                "73750713 972379732 642338590", "670116400 828958973 738774673", "855293372 56890051 124161121",
                "272791300 905306209 147348656", "831696632 76731002 554540746", "78927007 602453299 822341280",
                "386644769 973723585 496203227", "529459882 398034046 649560737", "839176288 649960685 791380638",
                "948823530 372013379 763084472", "85613955 974654491 67679323", "56890051 507067818 793899109",
                "56172121 76731002 331924687", "507336886 451913864 718362886", "507336886 615008389 416567596",
                "831696632 864250034 172558912", "860777366 975374128 130766152", "883682166 37747493 336764372",
                "842530005 563618035 825236268", "864250034 507336886 981039423", "855293372 76731002 845933707",
                "310407851 310407851 475629028", "76731002 76731002 285063015", "628256963 771501375 735186985",
                "56172121 864250034 695389252", "56890051 507336886 813508866", "319587773 347425295 64939079",
                "855293372 615008389 451127991", "855293372 864250034 801148088", "855293372 507067818 28187546",
                "451913864 274673668 214192662" );

        List<String> output = processLogs(logs, 3);
        System.out.println(output);
    }


}

    /*
    Sure! Here's a sample **Amazon Transaction Logs** coding question tailored for handling **large thresholds and large log datasets** — useful for interviews or system design prep.

---

### **🧾 Amazon Transaction Logs – High Volume Version**

#### **Problem Statement:**

You are given a list of transaction logs. Each log is a space-separated string containing three fields:

1. **Sender ID** (a string of digits)
2. **Recipient ID** (a string of digits)
3. **Amount** (an integer, which can be ignored for this task)

Your goal is to process these logs and **return a list of user IDs** that appear in **at least `threshold` number of transactions** — either as a sender or a recipient.

* A user only counts **once per transaction**, even if they are both sender and recipient.
* Return the list of qualifying user IDs sorted in **ascending numeric order**.

---

### **Function Signature (Java):**

```java
List<String> processLogs(List<String> logs, int threshold);
```

---

### **Input:**

* `logs`: A list of strings, where each string represents a transaction log in the format `"senderID recipientID amount"`.
* `threshold`: An integer representing the minimum number of transactions a user must be involved in to be included in the result.

---

### **Output:**

* Return a list of user IDs (as strings) involved in at least `threshold` transactions, sorted by numeric order.

---

### **Example Input:**

```java
logs = [
    "345366 89921 50",
    "029323 38239 30",
    "345366 38239 20",
    "029323 89921 10",
    "38239 38239 50"
]
threshold = 2
```

---

### **Expected Output:**

```java
["029323", "345366", "38239"]
```

---

### **Explanation:**

* "345366": appears twice → ✅
* "38239": appears 3 times (including a self-transaction) → ✅
* "029323": appears twice → ✅
* "89921": only once (the transaction with "029323" is double-counted with different roles, but it’s one transaction for each user) → ❌

---

### **Constraints:**

* `1 <= logs.length <= 10^6` (up to 1 million entries)
* `1 <= threshold <= 10^6`
* Each log contains valid space-separated values
* IDs are strings with leading zeroes allowed
* Amount is a positive integer but not relevant for processing

---

Would you like the optimized Java solution to handle this scale?

     */