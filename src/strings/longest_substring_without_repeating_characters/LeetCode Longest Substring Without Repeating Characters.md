**Tags:** #leetcode #java #algorithms #sliding-window #hashset **Dificuldade:** Medium **Data:** 26-12-2025

## Descrição do Problema

Dada uma string `s`, encontre o comprimento da **maior substring** sem caracteres repetidos.

- **Importante:** Substring é contígua (sequencial).
    
- **Exemplo "dvdf":** A resposta correta é 3 ("vdf"), não 2 ("dv" ou "df").
    

---

## Evolução da Solução

### 1. Tentativa Inicial (Falha Lógica & Sintaxe)

Minha primeira intuição foi usar `StringBuilder` e tentar "zerar" a busca assim que encontrasse um repetido.

```java
class Solution {

    public int lengthOfLongestSubstring(String s) {
        StringBuilder longest = new StringBuilder();
        int maxSize = 0;
  
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (longest.indexOf(s.valueOf(s.charAt(j))) != -1) {
                    longest.append(s.charAt(j));
                    if (longest.length() > maxSize) maxSize = longest.length();                    
                } else {
                    longest.setLength(0);
                    break;
                }  
            }
        }
        return maxSize;
    }
}
```

- **Erro de Lógica:** Ao encontrar um caractere repetido (ex: o segundo 'd' em "dvdf"), eu limpava o buffer inteiro (`setLength(0)`). Isso fazia eu perder o 'v' que estava antes, resultando em respostas menores que o correto.
    
- **Erro de Sintaxe:** Tentei desreferenciar `char` primitivo e usar métodos de String de forma confusa (`s.valueOf`), por esquecer de soluções que fazem _Autoboxing_.
    

### 2. Solução Final: Sliding Window com HashSet (6ms)

A correção veio ao entender o padrão **Sliding Window** (Janela Deslizante). Ao invés de zerar a janela, usamos dois ponteiros (`left` e `right`). Se acharmos um repetido, incrementamos o `left` (encolhemos a janela pela esquerda) **apenas o suficiente** para remover o conflito.

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> letters = new HashSet<>();
        int left = 0, maxSize = 0;
  
        for (int right = 0; right < s.length(); right++) 
            char current = s.charAt(right);
            while (letters.contains(current)) {
                letters.remove(s.charAt(left));
                left++;
            }

            letters.add(current);
  
            int actualSize = right - left + 1;
            maxSize = Math.max(maxSize, actualSize);
        }
        return maxSize;
    }
}
```

### 3. Curiosidade: Otimização Extrema (1ms) - _Gemini 3 Contribution_

Esta solução não foi a que eu implementei, mas foi apresentada pelo Gemini como forma de atingir o "Top 1%". **A Diferença:** Substitui o `HashSet` por um **Array de booleanos** (ou inteiros).

- **Por que é mais rápido?** Evita o custo de criar objetos `Character`, calcular `HashCode` e alocar memória na Heap. Usa a tabela ASCII (índice 0-255) para acesso direto à memória.

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        boolean[] seen = new boolean[256]; 
        int left = 0, maxSize = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            while (seen[c]) {
                seen[s.charAt(left)] = false;
                left++;
            }

            seen[c] = true;           
            maxSize = Math.max(maxSize, right - left + 1);
        }
        return maxSize;
    }
}
```

## Key Takeaways (Aprendizados)

1. **Sliding Window > Reset:** Quando buscar a maior sequência contígua, nunca "zere" o progresso ao achar um erro. Ajuste a janela inicial (`left++`) até o erro sumir.
    
2. **While vs If:** Para remover duplicatas, usamos `while` (remover repetidamente até limpar) e não `if` (remover apenas um), garantindo que a janela seja válida antes de prosseguir.
    
3. **Tipos Primitivos:** `HashSet<Character>` lida automaticamente com `char`. Não é preciso converter para String (`valueOf`).
    
4. **Performance Absoluta:** Para conjuntos de dados limitados e conhecidos (como caracteres ASCII), arrays simples batem HashMaps em velocidade.