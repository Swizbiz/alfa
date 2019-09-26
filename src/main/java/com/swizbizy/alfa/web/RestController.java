package com.swizbizy.alfa.web;

import com.swizbizy.alfa.model.Box;
import com.swizbizy.alfa.model.Item;
import com.swizbizy.alfa.model.ItemIdRequest;
import com.swizbizy.alfa.repository.BoxRepository;
import com.swizbizy.alfa.repository.ItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

    private final BoxRepository boxRepository;
    private final ItemRepository itemRepository;

    public RestController(BoxRepository boxRepository, ItemRepository itemRepository) {
        this.boxRepository = boxRepository;
        this.itemRepository = itemRepository;
    }

    @GetMapping("/box")
    public Iterable<Box> getBoxes() {
        return boxRepository.findAll();
    }

    @GetMapping("/item")
    public Iterable<Item> getItems() {
        return itemRepository.findAll();
    }

    @PostMapping("/itemsId")
    public List<Long> getItemsId(@RequestBody ItemIdRequest itemIdRequest) {
        Long boxId = itemIdRequest.getBox();
        String color = itemIdRequest.getColor();

        List<Box> boxes = new ArrayList<>(boxRepository.getBoxesByContainedIn(boxId));
        for (int i = 0; i < boxes.size(); i++) {
            Box elem = boxes.get(i);
            Long elemId = elem.getId();
            boxes.addAll(boxRepository.getBoxesByContainedIn(elemId));
        }

        List<Long> itemsId = new ArrayList<>(itemRepository.getItemIdByContainedInAndColor(boxId, color));
        for (Box elem : boxes) {
            Long elemId = elem.getId();
            itemsId.addAll(itemRepository.getItemIdByContainedInAndColor(elemId, color));
        }
        return itemsId;

    }
}
