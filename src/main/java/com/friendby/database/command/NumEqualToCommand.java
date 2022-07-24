package com.friendby.database.command;

import com.friendby.database.repository.StorageRepository;

import java.util.Map;

public class NumEqualToCommand extends CommandManipulation<Long> {

    private Map<String, Integer> memory;


    public NumEqualToCommand(String command) {
        super(command);
    }

    public NumEqualToCommand(String command, Map<String, Integer> memory) {
        super(command);
        this.memory = memory;
    }

    @Override
    public Long execute() {
        Integer value = Integer.valueOf(command.split(" ")[1]);
        if (memory != null && memory.containsValue(value)) {
            return memory.values().stream().filter(v -> v.intValue() == value).count();
        } else if (memory != null && memory.containsKey("unset" + value)) {
            return Long.valueOf(memory.get("unset" + value));
        } else
            return StorageRepository.countByValue(value);
    }
}
