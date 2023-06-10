package emu.nes.memory;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeMap;

/**
 * A memory that delegates read/write operations to one of other memories. The memory is selected
 * based on the address to read/write from/to. The passed address to the selected memory is offset
 * by the minimum address the memory is addressed to.
 */
public class SelectorByteMemory implements Memory {

    private final Map<Integer, Memory> memories;

    public SelectorByteMemory(Map<Integer, Memory> memories) {
        this.memories = new TreeMap<Integer, Memory>(memories);
    }

    @Override
    public byte read(final int addr) {
        Optional<? extends Entry<Integer, Memory>> selected = this.memory(addr);
        if (selected.isPresent()) {
            final Entry<Integer, Memory> entry = selected.get();
            return entry.getValue().read(addr - entry.getKey());
        }
        return 0;
    }

    @Override
    public void write(final int addr, final byte value) {
        Optional<? extends Entry<Integer, Memory>> selected = this.memory(addr);
        if (selected.isPresent()) {
            final Entry<Integer, Memory> entry = selected.get();
            entry.getValue().write(addr - entry.getKey(), value);
        }
    }

    /**
     * Accessor for the selector memories map.
     * @return A map of the selector memories
     */
    public Map<Integer, Memory> memories() {
        return this.memories;
    }

    /**
     * Selects the correct memory depending on the address.
     * @param addr Memory address
     * @return The memory corresponding to the address.
     */
    @SuppressWarnings("unchecked")
    private Optional<? extends Entry<Integer, Memory>> memory(int addr) {
        Optional<Entry<Integer, Memory>> result = Optional.empty();
        final Entry<Integer, Memory>[] entries = this.memories.entrySet().toArray(new Entry[0]);
        for (int idx = entries.length - 1; idx > -1; --idx) {
            if (addr >= entries[idx].getKey()) {
                result = Optional.of(entries[idx]);
                break;
            }
        }
        return result;
    }
}
