import shopAll from '@/assets/shop-all.svg'
import supplementsDefault from '@/assets/supplement-default.svg';
import foodDefault from '@/assets/food-default.svg';
import drinksDefault from '@/assets/drinks-default.svg';
import equipmentDefault from '@/assets/equipment-default.svg';
import defaultImage from '@/assets/default-image.svg';

export const resolveImage = (categoryId) => {
    switch (categoryId) {
        case 0:
            return shopAll;
        case 1:
            return supplementsDefault;
        case 2:
            return foodDefault;
        case 3:
            return drinksDefault;
        case 4:
            return equipmentDefault;
        default:
            return defaultImage;
    }
};