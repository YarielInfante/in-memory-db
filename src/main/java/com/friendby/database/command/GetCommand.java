package com.friendby.database.command;

import com.friendby.database.repository.StorageRepository;

import java.util.Map;

public class GetCommand extends CommandManipulation<Integer> {

    private Map<String, Integer> memory;

    public GetCommand(String command) {
        super(command);
    }


    public GetCommand(String command, Map<String, Integer> memory) {
        super(command);
        this.memory = memory;
    }

    @Override
    public Integer execute() {
        String key = command.split(" ")[1];
        if(memory != null && memory.containsKey(key)){
            return memory.get(key);
        }
        return StorageRepository.get(key);
    }
}
