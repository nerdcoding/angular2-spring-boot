import {PipeTransform, Pipe} from "@angular/core";

@Pipe({name: 'filter'})
export class FilterPipe implements PipeTransform {

    transform(filteredList: any[], filteredByField: string, filterValue: string): any {
        if (!filteredByField || !filterValue) {
            return filteredList;
        }

        return filteredList.filter(item => {
            const field = item[filteredByField].toLowerCase();
            const filter = filterValue.toLowerCase();
            return field.indexOf(filter) >= 0;
        });
    }
}