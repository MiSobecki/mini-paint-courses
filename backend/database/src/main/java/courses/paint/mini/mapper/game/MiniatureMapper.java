package courses.paint.mini.mapper.game;

import courses.paint.mini.entity.game.MiniatureEntity;
import courses.paint.mini.model.game.Miniature;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = FactionMiniatureMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MiniatureMapper {

    @Mapping(source = "faction", target = "faction", qualifiedByName = "toFactionWithoutMiniatures")
    @Named("toMiniature")
    Miniature toMiniature(MiniatureEntity miniatureEntity);

    @Mapping(source = "faction", target = "faction", qualifiedByName = "fromFactionWithoutMiniatures")
    @Named("fromMiniature")
    MiniatureEntity fromMiniature(Miniature miniature);

}
