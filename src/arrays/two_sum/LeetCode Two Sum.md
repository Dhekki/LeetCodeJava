**Tags:** #leetcode #java #algorithms #hashmap #arrays **Dificuldade:** Easy **Data:** 25-12-2025

## Descrição do Problema

Dado um array de inteiros `nums` e um inteiro `target`, retornar os índices dos dois números tais que a soma deles seja igual ao `target`.

- Existe exatamente uma solução.
    
- Não pode usar o mesmo elemento duas vezes.
    

**Exemplo:** `Input: nums = [2,7,11,15], target = 9` `Output: [0,1]` (pois 2 + 7 = 9)

**Constraints Importantes:**

- 2≤nums.length≤104 (O array pode ter até 10.000 itens, o que pune soluções quadráticas).
    

---

## Processo de Evolução

### 1. Abordagem Inicial: Força Bruta (Brute Force)

Minha primeira intuição foi testar todas as combinações possíveis. Para cada número, percorri o restante do array buscando seu par.

```java
for (int i = 0; i < nums.length; i++) {
    for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) {
            return new int[] { i, j };
        }
    }
}
```

- **Complexidade de Tempo:** O(n2). Para 10.000 elementos, isso gera até 100 milhões de operações.
    
- **Performance:** ~33ms no LeetCode.
    
- **Problema:** Lento para inputs grandes.
    

### 2. A Tentativa Intermediária (O Erro Lógico)

Tentei usar `Map` para otimizar, mas cometi dois erros conceituais:

1. Enchi o Map primeiro (um loop) e depois busquei (segundo loop).
    
2. Usei `map.containsValue(y)` dentro do loop. Como `containsValue` percorre o mapa inteiro, a complexidade voltou a ser O(n2).
    

### 3. Solução Final: One-Pass Hash Table

A sacada para chegar em O(n) é inverter a pergunta. Ao invés de olhar para o futuro (loop aninhado), olho para o **passado** (Map).

- Calculo o que falta: `complemento = target - atual`.
    
- Verifico se esse complemento já foi visto no Map.
    
- Se foi visto, retorno o índice visto + o índice atual, senão, adiciona no Map e continua buscando.

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> savedNumbers = new HashMap<>(nums.length);

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (savedNumbers.containsKey(complement))
                return new int[] {savedNumbers.get(complement), i};          

            savedNumbers.put(nums[i], i);
        }
        throw new IllegalArgumentException("No solution found");
    }
}
```

## Key Takeaways (Aprendizados)

1. **Troca de Espaço por Tempo:** Usar memória auxiliar (`HashMap`) permitiu reduzir o tempo de execução de 33ms para **~2ms**.
    
2. **Complexidade:** Saí de Quadrática O(n2) para Linear O(n).
    
3. **Clean Code:**
    
    - Evitar `return` de array vazio ou nulo quando a premissa do problema garante uma solução (usar `Exception`).
        
4. **Java Performance:**
    
    - `containsValue` em Map é O(n), evitar em loops.
        
    - Definir capacidade inicial do HashMap (`new HashMap<>(size)`) evita operações custosas de _rehashing_.
        
5. **Curiosidade (<1ms):** Soluções de 0ms em Java geralmente usam arrays primitivos simulando mapas para evitar o _overhead_ de Wrappers (`Integer`) e Hashing, mas são menos legíveis e "hacky" para o mundo real.




Pesquisar depois:
- _rehashing_