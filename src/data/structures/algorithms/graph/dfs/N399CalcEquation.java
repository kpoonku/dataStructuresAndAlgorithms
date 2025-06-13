package data.structures.algorithms.graph.dfs;

import java.util.*;

public class N399CalcEquation {
    // Test method
    public static void main(String[] args) {
        N399CalcEquation sol = new N399CalcEquation();
        List<List<String>> equations = Arrays.asList(Arrays.asList("a", "b"),
                Arrays.asList("b", "c"));
        double[] values = {2.0, 3.0};
        List<List<String>> queries = Arrays.asList(Arrays.asList("a", "c"),
                Arrays.asList("b", "a"), Arrays.asList("a", "e"),
                Arrays.asList("a", "a"), Arrays.asList("x", "x"));

        System.out.println(Arrays.toString(sol.calcEquation(equations, values, queries)));
        // Output: [6.0, 0.5, -1.0, 1.0, -1.0]
    }

    public double[] calcEquation(List<List<String>> equations,
                                 double[] values,
                                 List<List<String>> queries) {
        double[] results = new double[queries.size()];
        int i = 0;
        Map<String, Map<String, Double>> graph = new HashMap<>();

        for (List<String> equation : equations) {
            String a = equation.getFirst();
            String b = equation.getLast();
            double value = values[i++];

            graph.putIfAbsent(a, new HashMap<>());
            graph.putIfAbsent(b, new HashMap<>());
            graph.get(a).put(b, value);
            graph.get(b).put(a, 1.0 / value);
        }
        System.out.println(graph);
        for (i = 0; i < queries.size(); i++) {
            String src = queries.get(i).get(0);
            String dest = queries.get(i).get(1);

            if (!graph.containsKey(src) || !graph.containsKey(dest)) {
                results[i] = -1;
            } else if (src.equals(dest)) {
                results[i] = 1;
            } else {
                results[i] = dfsUsingStack(graph, src, dest);
            }
        }
        return results;
    }

    private double dfsUsingStack(Map<String, Map<String, Double>> graph,
                                 String src,
                                 String dest) {
        Stack<Pair> stack = new Stack<>();
        Set<String> visited = new HashSet<>();
        stack.push(new Pair(src, 1.0));
        while (!stack.isEmpty()) {
            Pair current = stack.pop();
            String node = current.var;
            double product = current.value;
            if (node.equals(dest)) return product;
            visited.add(node);
            for (Map.Entry<String, Double> neighbor : graph.get(node).entrySet()) {
                String nextNode = neighbor.getKey();
                double nextVal = neighbor.getValue();
                if (!visited.contains(nextNode)) {
                    stack.push(new Pair(nextNode, product * nextVal));
                }
            }
        }
        return -1.0;
    }

    class Pair {
        String var;
        double value;

        public Pair(String var, double value) {
            this.var = var;
            this.value = value;
        }
    }
}
/*
You are given an array of variable pairs equations and an array of real numbers values,
where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i].
Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you
must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result
in division by zero and that there is no contradiction.

Note: The variables that do not occur in the list of equations are undefined,
so the answer cannot be determined for them.

Example 1:
Input:
equations = [["a","b"],["b","c"]],
values = [2.0,3.0],
queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]

Explanation:
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
note: x is undefined => -1.0

Example 2:
Input: equations = [["a","b"],["b","c"],["bc","cd"]],
values = [1.5,2.5,5.0],
queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]

Example 3:
Input: equations = [["a","b"]],
values = [0.5],
queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
Output: [0.50000,2.00000,-1.00000,-1.00000]

Constraints:
1 <= equations.length <= 20
equations[i].length == 2
1 <= Ai.length, Bi.length <= 5
values.length == equations.length
0.0 < values[i] <= 20.0
1 <= queries.length <= 20
queries[i].length == 2
1 <= Cj.length, Dj.length <= 5
Ai, Bi, Cj, Dj consist of lower case English letters and digits.
*/
/*
Absolutely! Let's break this logic down **step by step** using a **real-world analogy**
so it makes sense for any human brain 😊

---

### 💡 THE PROBLEM — In Plain English

You are given a set of relationships like:

> "a divided by b equals 2"
> "b divided by c equals 3"

And someone asks you questions like:

> "What is a divided by c?"
> "What is b divided by a?"
> "What is a divided by e?" (You don’t even know what 'e' is)

---

### ✅ What Are We Doing?

We are solving **math puzzles** where some equations are known and you want to
**connect the dots** to find the answer to others.

---

### 🧠 HUMAN EXAMPLE

Say you know:

- 1 apple = 2 bananas → `a / b = 2`
- 1 banana = 3 cherries → `b / c = 3`

Now you want to figure out:

- How many cherries in 1 apple?

Let's work it out step by step:

```
1 apple = 2 bananas
1 banana = 3 cherries
So, 1 apple = 2 * 3 = 6 cherries
→ a / c = 6
```

We "linked" a → b → c and **multiplied** along the way.

---

### 🤖 Now Let's Translate That into a Program

We'll use a **graph**. Think of this like a map where each **variable**
(a, b, c) is a city, and each equation is a **road** with a **weight**.

#### Equations:
```
a / b = 2.0  → a —(2.0)→ b
              b —(0.5)→ a   (reverse direction)

b / c = 3.0  → b —(3.0)→ c
              c —(1/3.0)→ b
```

We build this map (graph) with **both directions**,
because you might need to go from `c → a` or `a → c`.

---

### 🧭 Answering a Query (a / c)

To figure this out:
- Start from `a`
- Try to find a path to `c`
- Multiply the weights as you go:
  - `a → b = 2`
  - `b → c = 3`
  - So: `a / c = 2 * 3 = 6`

---

### 🚶 Walkthrough in Human Steps

1. **Build the graph** from the equations. Each node connects to its neighbor with a value.
2. For each **query**, do a **Depth-First Search (DFS)**:
   - Start from the **first variable**
   - Walk through the graph, multiplying the values as you go
   - If you reach the **target variable**, return the result
   - If you can't reach it → return `-1`

---

### 💬 Real-Life Analogy: Currency Exchange

If:
- $1 USD = 0.85 EUR
- €1 EUR = 130 JPY

Then how many JPY is $1?

Easy:
- USD → EUR → JPY
- 1 * 0.85 * 130 = 110.5 JPY

We just multiplied along the path!

### 🛠️ So the Code Is Just Doing This:
- Build the graph of relationships
- For each question, walk the graph and multiply
- If a path doesn’t exist → return -1.0

 */
